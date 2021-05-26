package controllers;


import Task.Task;
import Task.TaskPage;

import java.util.Scanner;

public class Controller {
    Scanner controllerScanner;
    public static MenuEnum currentMenu = MenuEnum.CONTROLLER_MENU;
    MainMenu mainMenu ;
    RegisterMenu registerMenu;
    LoginMenu loginMenu;
    TaskPage taskPage;
    public void run(Scanner scanner){
        this.controllerScanner = scanner;
        while (currentMenu != MenuEnum.EXIT) {
            if(currentMenu == MenuEnum.CONTROLLER_MENU) {
                System.out.println("Welcome to trello!\nPlease type LOGIN for logging in or SIGN UP for register and EXIT for exit program.");
                String chooseMenu = controllerScanner.nextLine();
                if (chooseMenu.equalsIgnoreCase("LOGIN")) {
                    currentMenu = MenuEnum.LOGIN_MENU;
                } else if (chooseMenu.equalsIgnoreCase("SIGN UP")) {
                    currentMenu = MenuEnum.REGISTER_MENU;
                }
                else if (chooseMenu.equalsIgnoreCase("EXIT")){
                    currentMenu = MenuEnum.EXIT;
                }
                    else {
                    System.out.println("Invalid Command");
                }
            }
            else {
                if (currentMenu == MenuEnum.REGISTER_MENU) {
                    registerMenu = new RegisterMenu();
                    registerMenu.run(scanner);
                } else if (currentMenu == MenuEnum.LOGIN_MENU) {
                    loginMenu = new LoginMenu(scanner);
                    loginMenu.run();

                } else if (currentMenu == MenuEnum.MAIN_MENU) {
                    mainMenu = new MainMenu();
                    mainMenu.run();
                }
                else if (currentMenu == MenuEnum.TASK_PAGE){
                    if (Task.getIdOfTheTaskToShow()!=0){
                        taskPage = Task.getTaskById(Task.getIdOfTheTaskToShow()).getTaskPage();
                        taskPage.run();
                    }
                    else{
                        taskPage = Task.getTaskByTitle(Task.getTitleOfTheTaskToShow()).getTaskPage();
                        taskPage.run();
                    }
                }
            }

        }
    }
}
