package tracker;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ActionClass action = new ActionClass();
        System.out.println("Learning Progress Tracker");

        while (true) {
            String input = sc.nextLine();

            if (selectAction(action, input)) break;
        }
        sc.close();
    }

    private static boolean selectAction(ActionClass action, String input) {
        switch (input) {
            case "back" -> System.out.println("Enter 'exit' to exit the program.");
            case ""             -> System.out.println("No input");
            case "add students" -> action.addStudents();
            case "list"         -> action.listStudents();
            case "add points"   -> action.addPoints();
            case "find"         -> action.find();
            case "exit"         -> { System.out.println("Bye!");
                                    return true; }
            default             -> System.out.println("Error: unknown command!");
        }
        return false;
    }
//        if (input.equals("back")) {
//            System.out.println("Enter 'exit' to exit the program.");
//        } else if (input.isBlank()) {
//            System.out.println("No input.");
//        } else if (input.equals("add students")) {
//            action.addStudents();
//        } else if (input.equals("list")) {
//            action.listStudents();
//        } else if (input.equals("add points")) {
//            action.addPoints();
//        } else if (input.equals("find")) {
//            action.find();
//        }
//        else if (input.equals("exit")) {
//            System.out.println("Bye!");
//            return true;
//        } else {
//            System.out.println("Error: unknown command!");
//        }
//        return false;
}

