package user;

import Task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private final String fullName;
    private final String username;
    private String password;
    private  ArrayList<Task> assignedTasks = new ArrayList();
    private static  Map<String, User> users = new HashMap<>();
    private final String birthday;


    public User(String fullName,String username, String password,String birthday) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.birthday = birthday;
        users.put(username,this);
    }
    public static Map<String,User> getUsersList(){
        return users;
    }
    public String getFullName() {
        return this.fullName;
    }
    public String getPassword() {
        return this.password;
    }
    public String getBirthday() {
        return this.birthday;
    }
    public String getUsername() {
        return username;
    }
    public boolean isBirthDayCorrect(String birthday){
        return this.birthday.substring(0,10).equals(birthday.substring(0,10));
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }
    public void removeAssignedTask(String taskTitle) {
        for (Task tasks : assignedTasks) {
            if (tasks.getTitle().equals(taskTitle)) {
                assignedTasks.remove(tasks);
            }
        }
    }
    public void addAssignedTask(Task task){
        assignedTasks.add(task);
    }

    public ArrayList<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public  Task getTaskById(int id){
        for (Task tasks:assignedTasks) {
            if (tasks.getId()==id){
                return tasks;
            }
        }
        return null;
    }
    public  Task getTaskByTitle(String title){
        for (Task tasks:assignedTasks) {
            if (tasks.getTitle().equals(title)){
                return tasks;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getUsername();
    }
}
