package controllers;

import user.User;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    Scanner loginScanner;
    public static User currentUser = null;

    public LoginMenu(Scanner scanner) {
        this.loginScanner = scanner;
    }

    public void run(){
        System.out.println("Please enter your username:");
        String username = loginScanner.nextLine();
        if(username == "") {
            Controller.currentMenu = MenuEnum.CONTROLLER_MENU;
            return;
        }

        if (!User.getUsersList().containsKey(username)){
            System.out.println("There is no user with this username");
        }
        else{
            System.out.println("Please enter your password");
            String password = loginScanner.nextLine();
            if(password == "") {
                Controller.currentMenu = MenuEnum.CONTROLLER_MENU;
                return;
            }
            boolean isPasswordCorrect = User.getUsersList().get(username).isPasswordCorrect(password);
            if (!isPasswordCorrect){
                System.out.println("Password is not correct.\nDo you want to recover your password? Type y for yes and n for no.");
                String recoverYesOrNo = loginScanner.nextLine();
                if (recoverYesOrNo.equals("y")){
                    System.out.println("Please enter your birthday");
                    String birthday = loginScanner.nextLine();
                    if (User.getUsersList().get(username).isBirthDayCorrect(birthday)){
                        System.out.println("Please set your new password");
                        String newPassword;
                        while(true){
                            newPassword = loginScanner.nextLine();
                            Matcher passwordMatcher = Regex.passwordPattern.matcher(newPassword);
                            if (!passwordMatcher.find()){
                                System.out.println("Password is weak! Please try again.");
                                continue;
                            }
                            else{
                                User.getUsersList().get(username).setPassword(newPassword);
                                break;
                            }
                        }
                    }
                    else{
                        System.out.println("Birthdays do not match");
                    }
                }
            }
            else{
                LoginMenu.currentUser = User.getUsersList().get(username);
                Controller.currentMenu = MenuEnum.MAIN_MENU;
            }
        }
    }

}
