import command.Commands;
import manager.EventManager;
import manager.UserManager;
import model.Event;
import model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class Main implements Commands {
    private static Scanner scanner = new Scanner(System.in);
    private static UserManager userManager = new UserManager();
    private static EventManager eventManager = new EventManager();


    public static void main(String[] args) throws SQLException {
        Main.showCommands();

    }

    private static void showCommands() throws SQLException {
        boolean run = true;
        while (run) {
            Commands.printCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    run = false;
                    break;
                case ADD_EVENT:
                    addEvents();
                    break;
                case ADD_USER:
                    addUsers();
                    break;
                case SHOW_EVENTS:
                    eventManager.showAllEvents();
                    break;
                case SHOW_USERS:
                    userManager.showAllUser();
                    break;
                default:
                    System.out.println("Invalid command, please try again");
            }
        }
    }

    private static void addEvents() throws SQLException {
        System.out.println("Please input event name");
        String name = scanner.nextLine();
        System.out.println("Please input event place");
        String place = scanner.nextLine();
        System.out.println("Please input event isOnline");
        boolean isOnline = Boolean.parseBoolean(scanner.nextLine());
        System.out.println("Please input event price");
        double price = Double.parseDouble(scanner.nextLine());

        Event event = new Event();
        eventManager.addEvent(event);
    }

    private static void addUsers() throws SQLException {
        System.out.println("Please input user name");
        String name = scanner.nextLine();
        System.out.println("Please input user surname");
        String surname = scanner.nextLine();
        System.out.println("Please input user email");
        String email = scanner.nextLine();
        System.out.println("Please input user eventId");
        int eventId = Integer.parseInt(scanner.nextLine());

        User user = new User(name, surname, email, eventId);
        userManager.addUser(user);
    }
}

