package tracker;

import java.util.Scanner;

public class ActionClass {
    private static int studentCounter = 0;
    DataStore dataStore = new DataStore();
    Verifier verifier = new Verifier();
    Student student = null;
    Scanner scanner = new Scanner(System.in);

    public void addStudents() {
        System.out.print("Enter student credentials or 'back' to return:\n> ");
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("back")) {
                System.out.printf("Total %d students have been added\n", studentCounter);
                studentCounter = 0;
                return;
            } else { // try to add a student
                student = verifier.verifyStudentEntry(input);
                if (student != null) {
                    student.setStudentId(generateStudentId());
                    dataStore.addStudent(student);
                    System.out.println("The student has been added");
                    studentCounter++;
                }
            }
        }
    }

    private static int studentId = 1000;
    private static int generateStudentId() {
        return ++studentId;
    }

    public void addPoints() {
        // TODO: "add points" doesn't recognize "back" command after incorrect entry: eg: "asdf 1 2 2 3" -> "back"
        // TODO: fix - "find" should exit loop when "back" is entered
        // TODO: "list" command should print all ids and exit to main menu
        // TODO: fix - "add points" should not allow negative points
        System.out.println("Enter an id and points or 'back' to return:\n> ");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("back")) return;
            else { // keep adding points
                int[] scores = verifier.verifyStudentPointsEntry(input); // input should be in int, int, int, int format
                if (scores != null) {
                    dataStore.updateStudentScores(scores);
                }
            }
        }
    }

    public void listStudents() {
        dataStore.listStudentIds();
    }

    boolean studentAlreadyHasAccount(String email) {
        dataStore.studentAlreadyExists(email);
        return true;
    }

    public void find() {
        System.out.println("Enter an id or 'back' to return:");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("back")) break;
            try {
                int studentId = Integer.parseInt(input);
                dataStore.getStudentPoints(studentId);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input - please enter a number");
            }
        }
        return;
    }
}
