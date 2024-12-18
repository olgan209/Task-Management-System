import org.example.taskmanagementsystem.model.Task;
import org.example.taskmanagementsystem.model.TaskStatus;
import org.example.taskmanagementsystem.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private TaskService taskService;
    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        taskService = spy(new TaskService());
        doReturn(mockConnection).when(taskService).connect();
    }

    @Test
    public void testCreateTask() throws SQLException {
        Task task = new Task(50,
                "Test Task",
                "Description of the test task",
                TaskStatus.PENDING,
                1,
                java.time.LocalDateTime.of(2024, 12, 31, 12, 0)
        );

        taskService.createTask(task);

        verify(mockConnection).prepareStatement("INSERT INTO tasks(name, description, status, projectId, deadline) VALUES(?,?,?,?,?)");
        verify(mockStatement).setString(1, "Test Task");
        verify(mockStatement).setString(2, "Description of the test task");
        verify(mockStatement).setString(3, "PENDING");
        verify(mockStatement).setInt(4, 1);
        verify(mockStatement).setTimestamp(5, Timestamp.valueOf("2024-12-31 12:00:00"));
        verify(mockStatement).executeUpdate();
    }

    @Test
    public void testGetTaskById() throws SQLException {

        int taskId = 1;
        Task expectedTask = new Task(
                taskId,
                "Test Task",
                "Test Description",
                TaskStatus.PENDING,
                1,
                java.time.LocalDateTime.of(2024, 12, 31, 12, 0)
        );


        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true); // Симулируем, что есть данные
        when(mockResultSet.getInt("taskId")).thenReturn(expectedTask.getTaskId());
        when(mockResultSet.getString("name")).thenReturn(expectedTask.getName());
        when(mockResultSet.getString("description")).thenReturn(expectedTask.getDescription());
        when(mockResultSet.getString("status")).thenReturn(expectedTask.getStatus().toString());
        when(mockResultSet.getInt("projectId")).thenReturn(expectedTask.getProjectId());
        when(mockResultSet.getTimestamp("deadline")).thenReturn(Timestamp.valueOf(expectedTask.getDeadline()));


        Task resultTask = taskService.getTaskById(taskId);


        assertNotNull(resultTask);
        assertEquals(expectedTask.getTaskId(), resultTask.getTaskId());
        assertEquals(expectedTask.getName(), resultTask.getName());
        assertEquals(expectedTask.getDescription(), resultTask.getDescription());
        assertEquals(expectedTask.getStatus(), resultTask.getStatus());
        assertEquals(expectedTask.getProjectId(), resultTask.getProjectId());
        assertEquals(expectedTask.getDeadline(), resultTask.getDeadline());


        verify(mockConnection).prepareStatement("SELECT * FROM tasks WHERE taskId = ?");
        verify(mockStatement).setInt(1, taskId);
        verify(mockStatement).executeQuery();
        verify(mockResultSet).next();
        verify(mockResultSet).getInt("taskId");
        verify(mockResultSet).getString("name");
        verify(mockResultSet).getString("description");
        verify(mockResultSet).getString("status");
        verify(mockResultSet).getInt("projectId");
        verify(mockResultSet).getTimestamp("deadline");
    }
}
