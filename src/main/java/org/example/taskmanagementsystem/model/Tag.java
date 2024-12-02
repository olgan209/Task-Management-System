package main.java.org.example.taskmanagementsystem.model;

public class Tag {
    private int id;
    private String name;
    private int id_task;

    public Tag(int id, String name, int id_task) {
        this.id = id;
        this.name = name;
        this.id_task = id_task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_task() {
        return id_task;
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }
}
