import org.example.taskmanagementsystem.model.User;
import org.example.taskmanagementsystem.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class UserServiceTest {
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private UserService userService;

    @BeforeEach
    public void setUp() throws SQLException {
        // Создаем моки
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        userService = spy(new UserService());

        // Настроим метод connect() для возврата mockConnection
        doReturn(mockConnection).when(userService).connect();
    }

    @Test
    public void testCreateUser() throws Exception {
        // Настройка поведения существующих моков
        when(mockStatement.executeUpdate()).thenReturn(1);

        // Тестируемый объект
        User user = new User(25, "Test User", "test25@example.com", "password");

        // Вызываем метод для создания пользователя
        userService.createUser(user);

        // Проверяем корректность вызовов
        verify(mockConnection).prepareStatement("INSERT INTO User_table (name, email, password) VALUES (?,?,?)");
        verify(mockStatement).setString(1, "Test User");
        verify(mockStatement).setString(2, "test25@example.com");
        verify(mockStatement).setString(3, "password");
        verify(mockStatement).executeUpdate();
    }
}
