package controllers;

import user.User;

import java.util.Scanner;
import java.util.regex.Matcher;

public class RegisterMenu {
    public Scanner registerScanner = new Scanner(System.in);

    public void run(Scanner registerScanner) {
        String fullName;
        String username;
        String password;
        String passwordAgain;
        String birthday;

        System.out.print("You can start each menu from beginning only by pressing Enter button\n");


        System.out.print("Please enter your full name\n");
        fullName = registerScanner.nextLine();
        if (fullName.equalsIgnoreCase("")) {
            System.out.print("Start again:))\n");
            Controller.currentMenu = MenuEnum.CONTROLLER_MENU;
            return;
        }
        System.out.print("Please enter your username\n");
        while (true) {
            if(!registerScanner.hasNextLine()) return;
            username = registerScanner.nextLine();
            if (username.equalsIgnoreCase("")) {
                Controller.currentMenu = MenuEnum.CONTROLLER_MENU;
                return;

            }

            if (User.getUsersList().containsKey(username)) {
                System.out.print("Username has already been taken! Please try again.\n");
                continue;
            } else {
                break;
            }
        }
        System.out.print("Please enter your password\n");
        while (true) {
            if(!registerScanner.hasNextLine()) return;
            password = registerScanner.nextLine();
            Matcher passwordMatcher = Regex.passwordPattern.matcher(password);
            if (password.equalsIgnoreCase("")) {
                Controller.currentMenu = MenuEnum.CONTROLLER_MENU;
                return;

            }
            if (!passwordMatcher.find()) {
                System.out.print("Password is weak! Please try again.\n");
                continue;
            } else {
                break;
            }
        }
        System.out.print("Please enter your password again\n");
        while (true) {
            if(!registerScanner.hasNextLine()) return;
            passwordAgain = registerScanner.nextLine();
            if (passwordAgain.equalsIgnoreCase("")) {
                Controller.currentMenu = MenuEnum.CONTROLLER_MENU;
                return;

            }
            if (!password.equals(passwordAgain)) {
                System.out.print("Passwords do not match! Please try again\n");
                continue;
            } else {
                break;
            }
        }
        System.out.print("Please enter your birthday with this format: dd/mm/yyyy\n");
        while (true) {
            if(!registerScanner.hasNextLine()) return;
            birthday = registerScanner.nextLine();
            if (birthday.equalsIgnoreCase("")) {
                Controller.currentMenu = MenuEnum.CONTROLLER_MENU;
                return;

            }
            Matcher matcher = Regex.birthdayPattern.matcher(birthday);
            if (!matcher.find()) {
                System.out.print("Birthday format is not valid! Please try again\n");
                continue;
            } else {
                break;
            }
        }
        createUser(fullName, birthday, username, password);
        Controller.currentMenu = MenuEnum.CONTROLLER_MENU;
        return;

    }

    private void createUser(String fullName, String birthday, String username, String password) {
        new User(fullName, username, password, birthday);
        System.out.print("You registered successfully.\n");
    }

}




