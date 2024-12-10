package org.example.taskmanagementsystem.model;

public enum TaskStatus {
    TODO,
    IN_PROGRESS,
    COMPLETED;

    @Override
    public String toString() {
        switch (this) {
            case TODO:
                return "TODO";
            case IN_PROGRESS:
                return "IN_PROGRESS";
            case COMPLETED:
                return "COMPLETED";
            default:
                return super.toString();
        }
    }
}
