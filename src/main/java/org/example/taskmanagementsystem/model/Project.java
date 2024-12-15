package org.example.taskmanagementsystem.model;

public class Project {
    private Integer id; // Используется Integer для возможности работы с null
    private String name;
    private String description;

    // Конструктор без id
    public Project(String name, String description) {
        this(null, name, description); // id устанавливается как null
    }

    // Конструктор с id
    public Project(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Геттеры и сеттеры
    public Integer getId() { // Тип изменен на Integer для согласованности
        return id;
    }

    public void setId(Integer id) { // Тип изменен на Integer
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Переопределение метода toString для удобного вывода информации о проекте
    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
