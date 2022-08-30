package tracker;

import java.util.Scanner;

public class ActionClass {
    private static int studentCounter = 0;
    DataStore dataStore = new DataStore();
    Verifier verifier = new Verifier();
    Student student = null;
    Scanner scanner = new Scanner(System.in);
    Statistics statistics = new Statistics();

    public void addStudents() {
        System.out.println("Enter student credentials or 'back' to return");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equals("back")) {
                System.out.printf("Total %d students have been added\n", studentCounter);
                studentCounter = 0;
                return;
            } else { // try to add a student
                student = verifier.verifyStudentEntry(input);
                if (student != null) {
                    student.setStudentId(generateStudentId());
                    DataStore.addStudent(student);
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
        System.out.println("Enter an id and points or 'back' to return:");
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("back")) {
                System.out.println("Enter 'exit' to exit the program.");
                return; }

            int[] scores = verifier.verifyStudentPointsEntry(input); // input should be in int, int, int, int format
            if (scores != null) {
                dataStore.addLineOfStudentPoints(scores); // add to the log
                dataStore.updateStudentPointsTotal(scores); // update student points total
            }
        }
    }

    public void listStudents() {
        dataStore.printStudentIds();
    }

    boolean studentAlreadyHasAccount(String email) {
        DataStore.studentAlreadyExists(email);
        return true;
    }

    public void find() {
        System.out.println("Enter an id or 'back' to return:");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("back")) {
                System.out.println("Enter 'exit' to exit the program.");
                return; }

            try {
                int studentId = Integer.parseInt(input);
                if (dataStore.studentExists(studentId)) {
                    dataStore.printStudentPointsTotal(studentId);
                }else {
                    System.out.printf("No student is found for id=%d\n", studentId);
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input - please enter a number");
            }
        }
    }

    /*

     */
    public void showStatistics() {
        statistics.runStatistics();
    }
}
