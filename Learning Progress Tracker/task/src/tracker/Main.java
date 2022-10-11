package tracker;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ActionClass action = new ActionClass();

        final String APP_VERSION = "STAGE 4/5(Unfinished)";
        System.out.println("Learning Progress Tracker: " + APP_VERSION);

        while (true) {
            System.out.print("> ");
            String input = sc.nextLine();

            if (selectActionAndExit(action, input)) break;
        }
        sc.close();
    }

    private static boolean selectActionAndExit(ActionClass action, String input) {
        if (input.equals("back")) {
            System.out.println("Enter 'exit' to exit the program.");
        } else if (input.isBlank()) {
            System.out.println("No input.");
        } else if (input.equals("add students")) {
            action.addStudents();
        } else if (input.equals("list")) {
            action.listStudents();
        } else if (input.equals("add points")) {
            action.addPoints();
        } else if (input.equals("find")) {
            action.find();
        } else if (input.equals("exit")) {
            System.out.println("Bye!");
            return true;
        } else if (input.equals("statistics")) {
            action.showStatistics();
        } else {
            System.out.println("Error: unknown command!");
        }
        return false;
    }
}

