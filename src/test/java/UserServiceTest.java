import org.example.taskmanagementsystem.model.User;
import org.example.taskmanagementsystem.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private UserService userService;
    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        // Создаем моки
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        userService = spy(new UserService());

        doReturn(mockConnection).when(userService).connect();
    }

    @Test
    public void testCreateUser() throws Exception {
        when(mockStatement.executeUpdate()).thenReturn(1);

        User user = new User(25, "Test User", "test25@example.com", "password");

        userService.createUser(user);

        verify(mockConnection).prepareStatement("INSERT INTO User_table (name, email, password) VALUES (?,?,?)");
        verify(mockStatement).setString(1, "Test User");
        verify(mockStatement).setString(2, "test25@example.com");
        verify(mockStatement).setString(3, "password");
        verify(mockStatement).executeUpdate();
    }

    @Test
    public void testGetUser() throws SQLException {
        int userId = 1;
        User expectedUser = new User(userId, "Test User", "testuser@example.com");

        mockResultSet = mock(ResultSet.class);

        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("userId")).thenReturn(userId);
        when(mockResultSet.getString("name")).thenReturn("Test User");
        when(mockResultSet.getString("email")).thenReturn("testuser@example.com");

        User result = userService.getUser(userId);

        assertNotNull(result);
        assertEquals(expectedUser.getId(), result.getId());
        assertEquals(expectedUser.getName(), result.getName());
        assertEquals(expectedUser.getEmail(), result.getEmail());

        verify(mockConnection).prepareStatement("SELECT * FROM User_table WHERE userId = ?");
        verify(mockStatement).setInt(1, userId);
        verify(mockStatement).executeQuery();
        verify(mockResultSet).next();
    }

    @Test
    public void testGetUserNotFound() throws SQLException {
        int userId = 1;

        mockResultSet = mock(ResultSet.class);

        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        User result = userService.getUser(userId);

        assertNull(result);

        verify(mockConnection).prepareStatement("SELECT * FROM User_table WHERE userId = ?");
        verify(mockStatement).setInt(1, userId);
        verify(mockStatement).executeQuery();
        verify(mockResultSet, never()).getInt(anyString());
    }
}
