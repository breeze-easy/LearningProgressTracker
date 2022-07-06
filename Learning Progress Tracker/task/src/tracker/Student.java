package tracker;

public class Student {
    private String firstName;
    private String lastName;
    private String email;

    private Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Student(String studentInfo) throws Exception {
        this(studentInfo.split(" ")); // parse string into array
    }

    private Student (String [] studentInfo) throws Exception {
        if(studentInfo.length != 3) throw new Exception("Incomplete Student information");
        this.firstName = studentInfo[0];
        this.lastName = studentInfo[1];
        this.email = studentInfo[2];
    }

    public boolean verifyStudentInfo() throws Exception{
        verifyFirstName();
        verifyLastName();
        verifyEmail();
        return true;
    }

    private void verifyLastName() {
        Verifier.verifyLastName(lastName);
    }

    private void verifyEmail() {
    }

    private void verifyFirstName() {
    }

}
