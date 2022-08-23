package command;

public interface Commands {
    int LOGOUT = 0;
    int ADD_EVENT = 1;
    int ADD_USER = 2;
    int SHOW_EVENTS = 3;
    int SHOW_USERS = 4;


    static void printCommands() {
        System.out.println("please input " + LOGOUT + " for logout");
        System.out.println("please input " + ADD_EVENT + " for add event");
        System.out.println("please input " + ADD_USER + " for add user");
        System.out.println("please input " + SHOW_EVENTS + " for show all events");
        System.out.println("please input " + SHOW_USERS + " for show all users");

    }
}
