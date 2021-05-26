package Task;

import user.User;

import java.util.ArrayList;


public class Task{

    private static int idCounter = 1;
    private static ArrayList<Task> allTasks = new ArrayList<>();
    private User whoTaskIsAssignedTo = null;
    private final int id;
    private final String title;
    private String deadline;
    private TaskPage taskpage;
    private static int idOfTheTaskToShow = 0;
    private static String titleOfTheTaskToShow = null;

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Task(String title) {
        this.title=title;
        this.id=idCounter;
        this.taskpage = new TaskPage(this);
        idCounter++;
        allTasks.add(this);
    }

    public User getWhoTaskIsAssignedTo() {
        return whoTaskIsAssignedTo;
    }

    public void setWhoTaskIsAssignedTo(User whoTaskIsAssignedTo) {
        if (this.whoTaskIsAssignedTo!=null) {
            this.whoTaskIsAssignedTo.removeAssignedTask(this.title);
        }
        this.whoTaskIsAssignedTo = whoTaskIsAssignedTo;
        this.whoTaskIsAssignedTo.addAssignedTask(this);

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public static int getIdOfTheTaskToShow() {
        return Task.idOfTheTaskToShow;
    }

    public static String getTitleOfTheTaskToShow() {
        return Task.titleOfTheTaskToShow;
    }

    public String getDeadline() {
        return deadline;
    }

    public static ArrayList<Task> getAllTasks(){
        return allTasks;
    }
    public static Task getTaskById(int id){
        for (Task tasks:allTasks) {
            if (tasks.getId()==id){
                return tasks;
            }
        }
        return null;
    }
    public static Task getTaskByTitle(String title){
        for (Task tasks:allTasks) {
            if (tasks.getTitle().equals(title)){
                return tasks;
            }
        }
        return null;
    }

    public TaskPage getTaskPage(){
        return this.taskpage;
    }
    public static void setIdOfTheTaskToShow(int idOfTheTaskToShow){
       Task.idOfTheTaskToShow = idOfTheTaskToShow;
    }
    public static void setTitleOfTheTaskToShow(String titleOfTheTaskToShow){
        Task.titleOfTheTaskToShow = titleOfTheTaskToShow;
    }
    @Override
    public String toString() {
        return "id: "+id+"  "+"title: "+title;
    }


}
