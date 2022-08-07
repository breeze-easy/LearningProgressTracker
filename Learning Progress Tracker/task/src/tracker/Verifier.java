package tracker;

import java.util.Arrays;

public class Verifier {
    DataStore dataStore = new DataStore();

    Student verifyStudentEntry(String studentInfo) {
        boolean result = true;
        Student student = null;

        String firstName_regex = "[^'-]\\w*['|-]?\\w*[^'-]";
        String lastName_regex = "([^'-](\\w*['|-]?\\w*[^'-])+ )+";
        String email_regex = "[a-z\\d._-]+@[a-z\\d]+\\.[a-z\\d]+";
        String firstName;
        String lastName;
        String email;

        String[] fullInfArr = studentInfo.split(" ");
        if (fullInfArr.length < 3) {
            System.out.println("Incorrect credentials.");
            return null;
        }
        firstName = fullInfArr[0];
        email = fullInfArr[fullInfArr.length - 1];
        if (dataStore.studentAlreadyExists(email)) {
            System.out.println("This email is already taken.");
            return null;
        }
        lastName = studentInfo.substring(firstName.length() + 1, studentInfo.length() - email.length());

        if (!firstName.matches(firstName_regex)) {
            System.out.println("Incorrect first name.");
            result = false;
        } else if (!lastName.matches(lastName_regex) || !lastName.matches("([\\w|'|-]+\\s?)+")) {
            System.out.println("Incorrect last name.");
            result = false;
        } else if (!email.matches(email_regex)) {
            result = false;
            System.out.println("Incorrect email.");
        }

        if (result) {
            student  = new Student(firstName, lastName, email);
        }

        return student;
    }

    public int[] verifyStudentPointsEntry(String input) {
        String[]parts = input.split(" ");
        String strStudentId = parts[0];
        int[] scores = null;
        String message = "";
        try {
            int studentId = Integer.parseInt(strStudentId);
            if (dataStore.studentExists(studentId)) {
                scores = Arrays.stream(input.split(" "))
                        .mapToInt(Integer::parseInt).toArray();
            } else if (parts.length != 5) {
                message = "Incorrect points format.";
            } else {
                message = "No student is found for id=" + strStudentId;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            message = "Student Id must be numeric, but provided:" + strStudentId;
        }

        if(!message.isBlank()) {
            System.out.println(message);
        }
        return scores;

    }
    private boolean isNumeric(String s) {
        boolean result;
        try {
            int i = Integer.parseInt(s);
            if (i < 0) result = false;
            result = true;
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }
}

