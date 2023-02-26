package tracker;

import java.util.Arrays;

public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private String email;
    private boolean wasNotified;

    public boolean wasNotified() {
        return wasNotified;
    }

    public void setWasNotified(boolean wasNotified) {
        this.wasNotified = wasNotified;
    }

    private Integer[] testScores = new Integer[5]; //keep scores of 4 courses, [indexes]: 0-studentId, 1-Java, 2-DSA, 3-Databases, 4-Spring


    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", testScores=" + Arrays.toString(testScores) +
                '}';
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Integer[] getTestScores() {
        return testScores;
    }


    public void setTestScores(Integer[] testScores) {
        this.testScores = testScores;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
