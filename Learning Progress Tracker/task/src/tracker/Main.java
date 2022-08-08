package tracker;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ActionClass action = new ActionClass();
        String input;
        System.out.println("Learning Progress Tracker");

        while (true) {
//            System.out.print("Please enter command \n> ");
            input = sc.nextLine();

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
            }
            else if (input.equals("exit")) {
                System.out.println("Bye!");
                break;
            } else {
                System.out.println("Error: unknown command!");
            }
        }
        sc.close();
    }
}

