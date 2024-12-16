package org.example.taskmanagementsystem.model;

public enum TaskStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED;

    public static TaskStatus fromString(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        switch (status.toLowerCase().replace(" ", "_")) {
            case "pending":
                return PENDING;
            case "in_progress":
            case "in progress": // для совместимости
                return IN_PROGRESS;
            case "completed":
                return COMPLETED;
            default:
                throw new IllegalArgumentException("Unknown TaskStatus: " + status);
        }
    }
}