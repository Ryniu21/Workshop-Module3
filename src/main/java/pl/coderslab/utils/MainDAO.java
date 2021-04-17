package pl.coderslab.utils;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainDAO {
    public static void main(String[] args) {
        boolean close = false;
        String command;
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);

        while (!close) {
            System.out.println();
            System.out.println(ConsoleColors.BLUE + "Please select next command on database:");
            System.out.println(ConsoleColors.GREEN + "add user");
            System.out.println(ConsoleColors.GREEN + "show database");
            System.out.println(ConsoleColors.GREEN + "show user");
            System.out.println(ConsoleColors.GREEN + "update user");
            System.out.println(ConsoleColors.RED + "remove user");
            System.out.println(ConsoleColors.RED + "close" + ConsoleColors.RESET);
            command = scanner.nextLine();

            switch (command){
                case "add user":
                    System.out.println("Please type userName:");
                    String userName = scanner.nextLine();
                    System.out.println("Please type email address:");
                    String userMail = scanner.nextLine();
                    System.out.println("Please type password:");
                    String password = scanner.nextLine();
                    User user = new User(userName, userMail, password);
                    UserDao.create(user);
                    break;

                case "show database":
                    System.out.println(Arrays.toString(UserDao.findAll2()));
                    //UserDao.findAll2();
                    break;

                case "show user":
                    System.out.println("Please type respondent id to show:");
                    try{
                        int idToShow = scanner2.nextInt();
                        System.out.println(UserDao.read(idToShow));
                    } catch(InputMismatchException e){
                        System.out.println("Expected number");
                    }
                    scanner2 = new Scanner(System.in);
                    break;

                case "update user":
                    System.out.println("Please type userId to update:");
                    int idToUpdate;
                    try{
                        idToUpdate = scanner2.nextInt();
                        System.out.println("Type updated data:");
                        System.out.println("UserName:");
                        String updateUserName = scanner.nextLine();
                        System.out.println("Email address:");
                        String updateUserMail = scanner.nextLine();
                        System.out.println("Password:");
                        String updatePassword = scanner.nextLine();

                        User updateUser = new User(idToUpdate, updateUserMail, updateUserName, updatePassword);
                        UserDao.update(updateUser);
                    } catch(InputMismatchException e){
                        System.out.println("Expected number");
                    }
                    scanner2 = new Scanner(System.in);
                    break;

                case "remove user":
                    System.out.println("Please type respondent id to remove:");
                    try{
                        int idToRemove = scanner2.nextInt();
                        User removeUser = new User(idToRemove);
                        UserDao.remove(removeUser);
                    } catch(InputMismatchException e){
                        System.out.println("Expected number");
                    }
                    scanner2 = new Scanner(System.in);
                    break;

                case "close":
                    close=true;
                    break;
                default:
                    System.out.println("Please type one of options listed");
            }
        }

    }
}
