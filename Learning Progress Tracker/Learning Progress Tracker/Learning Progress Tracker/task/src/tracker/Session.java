package tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Session {
    private final List<Student> newStudentsList;
    private int studentsCount;
    private Enum<mode> mode;
    private Storage storage;

    public Session(Enum<tracker.mode> mode, Storage storage) {
        this.mode = mode;
        this.storage = storage;
        newStudentsList = new ArrayList<>();
    }

    public int getStudentCount() {
        return newStudentsList.size();
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
                    if(mode == tracker.mode.TOPMENU) {
                        UI.println("Enter 'exit' to exit the program");
                    }else if (mode == tracker.mode.ADDSTUDENTS) {
                        UI.println("Total " + studentsCount + " students have been added.");
                    }else UI.printMessageUnknownCommand();
                    break;
                case "add students" :
                    mode = tracker.mode.ADDSTUDENTS;
                    UI.printMenuAddStudents();
                    while (true){
                        System.out.print("> ");
                        String userInput = getUserInput();

                        if(userInput.trim().equalsIgnoreCase("back")) {
                            mode = tracker.mode.TOPMENU;
                            UI.println("Total " + newStudentsList.size() + " students have been added.");
                            storage.saveStudents(newStudentsList);
                            newStudentsList.clear();
                            break;
                        }else { // add student
                            Student student =  Verifier.verifyAndCreateNewStudent(userInput);
                            if (student != null) {
                                addStudent(student);
                                UI.println("The student has been added.");
                            }
                        }
                    }
                    break;
                case "end": case "exit":
                    return; // to end() in main()
                default:
                    UI.printMessageUnknownCommand();
            }
        }
    }

    private void addStudent(Student student) {
        try {
            newStudentsList.add(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static private String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}