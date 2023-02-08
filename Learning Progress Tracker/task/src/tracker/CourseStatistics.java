package tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseStatistics {

    private List<int[]> studentPointsTransactionLog;
    int myCourseIndex;

    CourseStatistics(String courseName){
        this.courseName = courseName;
        this.myCourseIndex = setCourseIndex(courseName);
//        this.studentPointsTransactionLog = DataStore.getStudentPointsTransactionLog();
        loadStatsFromDataStore(myCourseIndex);
    }

    private int setCourseIndex(String courseName) {
        return switch (courseName){
            case "java" -> 1;
            case "dsa" -> 2;
            case "databases" -> 3;
            case"spring" -> 4;
            default -> throw new IllegalStateException("Unexpected value: " + courseName);
        };
    }

    private void loadStatsFromDataStore(int myCourseIndex) {
//        List<int[]> studentPointsTransactionLog = DataStore.getStudentPointsTransactionLog();
        numberOfRegisteredStudents = loadNumberOfRegisteredStudents(myCourseIndex);
        numberOfSubmissions = loadNumberOfSubmissions(myCourseIndex);
        totalOfAllSubmissions = loadSumOfAllSubmissions(myCourseIndex);
        mapOfStudents = loadMapOfStudents(myCourseIndex);
    }

    private Map<Integer, Student> loadMapOfStudents(int myCourseIndex) {
        Map<Integer, Student> studentMap = DataStore.getStudents();



        return studentMap;
    }

    private int loadSumOfAllSubmissions(int myCourseIndex) { // sum of all submission points for the course
        int sumOfAllSubmissionPoints = 0;
        List<int[]> studentPointsTransactionLog = DataStore.getStudentPointsTransactionLog();
        for(int[] arrLine : studentPointsTransactionLog)
            if(arrLine[myCourseIndex] != 0) sumOfAllSubmissionPoints += arrLine[myCourseIndex];
       return sumOfAllSubmissionPoints;
    }

    private int loadNumberOfSubmissions(int myCourseIndex) {
        int numberOfSubmissions = 0;
        List<int[]> studentPointsTransactionLog = DataStore.getStudentPointsTransactionLog();
        for (int[] arrLine : studentPointsTransactionLog)
            if (arrLine[myCourseIndex] != 0) numberOfSubmissions++;
        return numberOfSubmissions;
    }

    int loadNumberOfRegisteredStudents(int myCourseIndex) {
        int numberOfStudents = 0;
        Map<Integer, int[]> studentsPointTotal = DataStore.getStudentPointsTotal();

        for(Map.Entry<Integer, int[]> entry : studentsPointTotal.entrySet()){
            int[] arrLine = entry.getValue(); // array containing total student scores
            if(arrLine[myCourseIndex] !=0){ numberOfStudents++;}
        }
        return numberOfStudents;
    }

    List<int[]> listOfStudentsWithScores(int myCourseIndex) {
        List<int[]> studentScoresList = new ArrayList<>();
        Map<Integer, int[]> studentsPointTotal = DataStore.getStudentPointsTotal();

        for(Map.Entry<Integer, int[]> entry : studentsPointTotal.entrySet()){
            int[] arrLine = entry.getValue(); // array containing total student scores
            if(arrLine[myCourseIndex] !=0){
                int studentId = arrLine[0];
                int points = arrLine[myCourseIndex];
                studentScoresList.add(new int[] {studentId,points});
            }
        }
        return studentScoresList;
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

    void setNumberOfSubmissions(int numberOfSubmissions) {
        this.numberOfSubmissions = numberOfSubmissions;
    }

    public int getTotalOfAllSubmissions() {
        return totalOfAllSubmissions;
    }

    void setTotalOfAllSubmissions(int totalOfAllSubmissions) {
        this.totalOfAllSubmissions = totalOfAllSubmissions;
    }

    public Map<Integer,Student> getMapOfStudents() {
        return mapOfStudents;
    }

    public void setMapOfStudents(Map<Integer, Student> mapOfStudents) {
        this.mapOfStudents = mapOfStudents;
    }

    private String courseName;
    private int numberOfRegisteredStudents;
    private int numberOfSubmissions;
    private int totalOfAllSubmissions;
    private Map<Integer,Student> mapOfStudents;
}
