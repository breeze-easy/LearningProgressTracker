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

    public Integer[] verifyStudentPointsEntry(String input) {
        Integer[] result;
        int[] scores;
        String[]parts = input.split(" ");
        int studentId = 0;
        String message = "";

        try { studentId = Integer.parseInt(parts[0]); }
        catch (NumberFormatException numberFormatException) {
            System.out.println("No student is found for id=" + parts[0]);
            return null; }

        if (parts.length != 5) {
            System.out.println("Incorrect points format.");
            return null; }

        if (dataStore.studentExists(studentId)) {
            try {
                scores = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
                result = Arrays.stream(scores).boxed().toArray(Integer[]::new);

            } catch (Exception e) {
                System.out.println("Incorrect points format.");
                return null; }}
        else { System.out.println("No student is found for id=" + studentId);
            return null; }

        for (int i = 1; i < result.length; i++) {
            if (result[i] < 0) {
                System.out.println("Incorrect points format. Points must be positive numbers");
                return null; }}

        return result;
    }

}

