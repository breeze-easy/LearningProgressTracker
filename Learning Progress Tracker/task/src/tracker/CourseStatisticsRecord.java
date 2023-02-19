package tracker;

import java.util.ArrayList;
import java.util.List;

public record CourseStatisticsRecord(Course course, int numOfRegisteredStudents, int numberOfSubmissions
            , int totalSubmissionsScore, double avgSubmissionGrade){


}

class RecordRunner{
    public static void main(String[] args)  {
        List<CourseStatisticsRecord> courseStatisticsList = new ArrayList<>();

        /*DataStore.updateStudentPointsTotal(new int[]{1001, 1, 2, 3, 4});
        DataStore.updateStudentPointsTotal(new int[]{1002, 1, 2, 3, 0});
        DataStore.updateStudentPointsTotal(new int[]{1003, 1, 2, 0, 0});
        DataStore.updateStudentPointsTotal(new int[]{1004, 1, 10, 0, 0});
        DataStore.updateStudentPointsTotal(new int[]{1005, 1, 50, 2, 0});
        DataStore.updateStudentPointsTotal(new int[]{1006, 1, 0, 0, 8});*/

        DataStore dataStore = new DataStore();
        dataStore.addLineOfStudentPoints(new Integer[]{1001, 1,2,3,0});
        dataStore.addLineOfStudentPoints(new Integer[]{1001, 7,2,3,0});
        dataStore.addLineOfStudentPoints(new Integer[]{1002, 1,0,3,4});
        dataStore.addLineOfStudentPoints(new Integer[]{1001, 1,2,3,4});
        dataStore.addLineOfStudentPoints(new Integer[]{1003, 1,2,3,4});
        
        int numOfStudents = 0;
        int numOfSubmissions =0;
        int totalSubmissionsScore =0;
        double avgGrade =0 ;
        List<int[]> listOfStudentScores;

        for(var course: Course.values()) {
            numOfStudents = Statistics.getNumberOfStudentsPerCourse(course);
            numOfSubmissions = Statistics.getNumberOfSubmissionsPerCourse(course);
            totalSubmissionsScore = Statistics.getTotalSubmissionsScore(course);
            avgGrade = totalSubmissionsScore / numOfSubmissions;
            listOfStudentScores = DataStore.getStudentPointsTotal().values().stream()
                    .map(x -> new int[] {x[0], x[1]})
                    .toList();

            courseStatisticsList.add(new CourseStatisticsRecord(course, numOfStudents,numOfSubmissions, totalSubmissionsScore, avgGrade));
        }
//        courseStatisticsList.forEach(System.out::println);
//        System.out.println("\n");

        Statistics.runStatistics();

    }

    private static int getNumberOfSubmissions(Course course) {
        return switch (course) {
            case Java -> 10;
            case DSA -> 20;
            case Databases -> 30;
            case Spring -> 40;
            default -> throw new IllegalArgumentException("Unexpected course name:" + course);
        };
   }

   private static int calcTotalOfSubmissions(Course course){
       return switch (course) {
           case Java -> 100;
           case DSA -> 200;
           case Databases -> 30;
           case Spring -> 40;
           default -> throw new IllegalArgumentException("Unexpected course name:" + course);
       };
   }

}