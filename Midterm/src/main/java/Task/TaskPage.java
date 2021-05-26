package Task;

import controllers.Controller;
import controllers.MenuEnum;
import controllers.Regex;
import user.User;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TaskPage {
    Scanner taskPageScanner = new Scanner(System.in);

    private Task whichTaskDoesThisPageRefersTo;

    public TaskPage(Task task){
        this.whichTaskDoesThisPageRefersTo = task;
    }

    public void run(){
        System.out.println(taskInfo());
        System.out.println("\n");
        System.out.println("1.Set Deadline\n2.Assign A Task\n3.Return To Main Menu");
        System.out.println("System.out.println(\"To use any of the commands above please type number show at the first of them then press Enter button\");\n");
        String enterCommand = taskPageScanner.nextLine();
        if (enterCommand.equals("1")){
            System.out.println("Please enter deadline with this format: dd/mm/yyyy hh:mm");
            String deadLine = taskPageScanner.nextLine();
            Matcher matcher = Regex.deadlinePattern.matcher(deadLine);
            if (!matcher.find()){
                System.out.println("Deadline format is not valid! Please try again");
            }
            else {
                setDeadLine(deadLine);
            }
        }
        else if (enterCommand.equals("2")){
            System.out.println("Please write down the username you want this task to be assigned to.( Remember you can press Enter where ever you decided to exit :) )");
            String username = "";
            while (true) {
                username = taskPageScanner.nextLine();
                if (username.equals("")){
                    break;
                }
                if (!User.getUsersList().containsKey(username)) {
                    System.out.println("Username does not exist (You can press Enter to return to task page)");
                    continue;
                } else {
                    System.out.println(User.getUsersList().get(username).getFullName());
                    System.out.println("Is this the user you are looking for? (Type y for yes and n for no)");
                    String yeOrNoCommand = taskPageScanner.nextLine();
                    if (yeOrNoCommand.equals("y")){
                        assignTask(username);
                    }
                    if (yeOrNoCommand.equals("n")){
                        break;
                    }

                }
            }
        }


        else if (enterCommand.equals("3")){
            Controller.currentMenu = MenuEnum.MAIN_MENU;
        }


    }
    private void setDeadLine(String deadLine){
        whichTaskDoesThisPageRefersTo.setDeadline(deadLine);
    }
    private void assignTask(String username){
        whichTaskDoesThisPageRefersTo.setWhoTaskIsAssignedTo(User.getUsersList().get(username));
    }
    private String taskInfo(){
        int taskId = whichTaskDoesThisPageRefersTo.getId();
        String taskTitle = whichTaskDoesThisPageRefersTo.getTitle();
        String deadLine = whichTaskDoesThisPageRefersTo.getDeadline();
        if (deadLine==null){
            deadLine="";
        }
        return "Task ID: "+taskTitle+"\nTask Title: "+taskTitle+"\nDeadLine: "+deadLine;
    }
}

