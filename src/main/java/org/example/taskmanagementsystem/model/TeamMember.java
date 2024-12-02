package main.java.org.example.taskmanagementsystem.model;

public class TeamMember {
    private int id;
    private int userId;
    private int projectId;

    public TeamMember(int id, int userId, int projectId) {
        this.id = id;
        this.userId = userId;
        this.projectId = projectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
