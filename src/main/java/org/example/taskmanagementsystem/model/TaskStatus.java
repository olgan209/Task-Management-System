package org.example.taskmanagementsystem.model;

public enum TaskStatus {
    Todo,
    In_Progress,
    Completed;

    @Override
    public String toString() {
        switch (this) {
            case Todo:
                return "TODO";
            case In_Progress:
                return "IN PROGRESS";
            case Completed:
                return "COMPLETED";
            default:
                return super.toString();
        }
    }
}
