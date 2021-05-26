package controllers;

import Task.Task;
import user.User;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    private  User currentUser;
    Scanner mainMenuScanner = new Scanner(System.in);
    public MainMenu(){
        this.currentUser = LoginMenu.currentUser;
        System.out.println("Username: "+currentUser.getUsername());
        System.out.println("Full name: "+currentUser.getFullName());
    }
    public void run(){
        System.out.println("1.Show All Tasks\n2.Show User Assigned Tasks\n3.Add New Task\n4.Return To Login And Register Menu");
        System.out.println("To use any of the commands above please type number show at the first of them then press Enter button");
        String enterCommand = mainMenuScanner.nextLine();
        if (enterCommand.equals("1")){
            showAllTasks();
            String chooseTask = mainMenuScanner.nextLine();
            Matcher matcherDigit = Regex.onlyDigitPattern.matcher(chooseTask);
            Matcher matcherLetter = Regex.onlyLetterOrDigitPattern.matcher(chooseTask);
            if (matcherDigit.find() || matcherLetter.find()) {
                try {
                    if (Integer.parseInt(chooseTask)!=0){
                        int id = Integer.parseInt(chooseTask);

                        Task.setIdOfTheTaskToShow(id);
                        Task.setTitleOfTheTaskToShow(null);
                    }
                }
                catch (NumberFormatException e){

                    Task.setTitleOfTheTaskToShow(chooseTask);
                    Task.setIdOfTheTaskToShow(0);
                }
                if (Task.getTaskByTitle(Task.getTitleOfTheTaskToShow())==null && Task.getTaskById(Task.getIdOfTheTaskToShow())==null){
                    System.out.println("Wrong id or title. Please try again");
                }
                 else {
                    Controller.currentMenu = MenuEnum.TASK_PAGE;
                }
            }
            else{
                System.out.println("Invalid Command");
            }

        }
        else if (enterCommand.equals("2")){
            showAllAssignedTasks();
            String chooseTask = mainMenuScanner.nextLine();
            Matcher matcherDigit = Regex.onlyDigitPattern.matcher(chooseTask);
            Matcher matcherLetter = Regex.onlyLetterOrDigitPattern.matcher(chooseTask);
            if (matcherDigit.find() || matcherLetter.find()) {
                try {
                    if (Integer.parseInt(chooseTask) != 0) {
                        int id = Integer.parseInt(chooseTask);
                        Task.setIdOfTheTaskToShow(id);
                        Task.setTitleOfTheTaskToShow(null);
                    }
                } catch (NumberFormatException e) {
                    Task.setTitleOfTheTaskToShow(chooseTask);
                    Task.setIdOfTheTaskToShow(0);
                }
                if (Task.getTaskByTitle(Task.getTitleOfTheTaskToShow())==null && Task.getTaskById(Task.getIdOfTheTaskToShow())==null){
                    System.out.println("Wrong id or title. Please try again");
                }
                else {
                    Controller.currentMenu = MenuEnum.TASK_PAGE;
                }
            }
            else{
                System.out.println("Invalid Command");
            }

        }
        else if (enterCommand.equals("3")){
            System.out.println("Please write down the title of your task");
            String taskTitle = mainMenuScanner.nextLine();
            addNewTask(taskTitle);
        }
        else if (enterCommand.equals("4")){
            Controller.currentMenu = MenuEnum.CONTROLLER_MENU;
        }
    }


    private void showAllTasks(){
        System.out.println("All Tasks:");
        for (Task tasks: Task.getAllTasks()) {
            System.out.println(tasks);
        }
        System.out.println("To enter the page of any task please type the id or title of a task ,then press Enter button");
    }
    private void showAllAssignedTasks(){
        System.out.println("Assigned Tasks:");
        for (Task tasks: currentUser.getAssignedTasks()) {
            System.out.println(tasks);
        }
        System.out.println("To enter the page of any task please type the id or title of a task ,then press Enter button");
    }
    private void addNewTask(String taskTitle){
        new Task(taskTitle);
    }
}
