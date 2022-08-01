package tracker;

public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private String email;

    Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int[] getTestScores() {
        return testScores;
    }

    private int[] testScores = new int[4];


    public void setTestScores(int[] testScores) {
        this.testScores = testScores;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
