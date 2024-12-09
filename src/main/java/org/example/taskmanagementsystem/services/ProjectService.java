package main.java.org.example.taskmanagementsystem.services;

import main.java.org.example.taskmanagementsystem.model.Project;
import main.java.org.example.taskmanagementsystem.model.User;

import java.util.ArrayList;

public class ProjectService {
    public void createProject(){

    }
    public void addUserToProject(){}
    public Project getProjectById(){return null;}
    public ArrayList<Project> getAllProjects(){return null;}
    public Project getProjectByUser(int id){return null;}
    public ArrayList<User> getProjectUsers(int id){return null;}
    public void updateProject(int id, Project updatedProject){}
    public void addTaskToProject(int id, int taskId){}
    public void removeTaskFromProject(int id, int taskId){}
    public void deleteTaskFromProject(int id, int taskId){}
    public void deleteProjectById(int id){}
}
