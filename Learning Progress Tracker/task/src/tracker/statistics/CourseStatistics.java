package tracker.statistics;

import tracker.Student;

import java.util.List;

public class CourseStatistics {

    CourseStatistics(String courseName){
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }


    public int getNumberOfRegisteredStudents() {
        return numberOfRegisteredStudents;
    }

    public void setNumberOfRegisteredStudents(int numberOfRegisteredStudents) {
        this.numberOfRegisteredStudents = numberOfRegisteredStudents;
    }

    public int getNumberOfSubmissions() {
        return numberOfSubmissions;
    }

    public void setNumberOfSubmissions(int numberOfSubmissions) {
        this.numberOfSubmissions = numberOfSubmissions;
    }

    public int getTotalOfSubmissions() {
        return totalOfSubmissions;
    }

    public void setTotalOfSubmissions(int totalOfSubmissions) {
        this.totalOfSubmissions = totalOfSubmissions;
    }

    public List<Student> getListOfStudents() {
        return listOfStudents;
    }

    public void setListOfStudents(List<Student> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }

    private String courseName;
    private int numberOfRegisteredStudents;
    private int numberOfSubmissions;
    private int totalOfSubmissions;
    private List<Student> listOfStudents;
}
