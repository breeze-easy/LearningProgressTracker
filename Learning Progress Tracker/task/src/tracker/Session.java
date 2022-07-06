package tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Session {
    private final List<Student> studentList;
    private int studentsCount;

    public Session () {
        studentList = new ArrayList<>();
    }

    public int getStudentCount() {
        return studentsCount = studentList.size();
    }

    public void processUserInput() {
        while (true) {
            System.out.print("> ");
            String command = getUserInput().trim();
            switch (command) {
                case "" :
                    UI.printNoInput();
                    break;
                case "back":
                    UI.println("Enter 'exit' to exit to program");
                    break;
                case "add students" :
//                    System.out.println("Please enter student credentials or 'back' to return");
                    UI.printMenuAddStudents();
                    String userInput = getUserInput();
                    if(userInput.trim().equalsIgnoreCase("back")) {
                        UI.println("Total " + studentsCount + " students have been added.");
                    }else { // must be student info
                        addStudent(userInput);
                    }
                    break;
                case "end": case "exit":
                    return; // to end() in main()
                default:
                    UI.printMessageUknownCommand();
            }
        }
    }

    private void addStudent(String studentInfo) {
        try {
            studentList.add(new Student(studentInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static private String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
