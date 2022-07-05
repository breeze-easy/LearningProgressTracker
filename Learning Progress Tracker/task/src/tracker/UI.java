package tracker;

public class UI {
    static final String MSG_PROGRAM_TITLE = "Learning Progress Tracker";
    static final String MSG_GOOD_BUY = "Bye!";
    static final String MSG_UNKNOWN_COMMAND = "Unknown command!";
    static final String MSG_NO_INPUT = "No input";
    static final String COMMAND_EXIT = "exit";

    static public void printProgramTitle(){
        System.out.println(MSG_PROGRAM_TITLE);
    }

    public static void printNoInput() {
        System.out.println(MSG_NO_INPUT);
    }

    public static void printMenuAddStudents() {
        System.out.println("Please enter student credentials or 'back' to return");
    }

    public static void println(String s) {
        System.out.println(s);
    }

    public static void printMessageUnknownCommand() {
        System.out.println(MSG_UNKNOWN_COMMAND);
    }

    public static void printGoodBye() {
        System.out.println(MSG_GOOD_BUY);
    }

    public static void print(String s) {
        System.out.print(s);
    }
}
