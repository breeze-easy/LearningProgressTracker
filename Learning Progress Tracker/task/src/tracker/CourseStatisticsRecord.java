package tracker;

import java.util.ArrayList;
import java.util.List;

public record CourseStatisticsRecord(Course course, int numOfRegisteredStudents, int numberOfSubmissions){}

class RecordRunner{
    public static void main(String[] args)  {
        List<CourseStatisticsRecord> courseStatisticsList = new ArrayList<>();

        DataStore.updateStudentPointsTotal(new int[]{1001, 1, 2, 3, 4});
        DataStore.updateStudentPointsTotal(new int[]{1002, 1, 2, 3, 0});
        DataStore.updateStudentPointsTotal(new int[]{1003, 1, 2, 0, 0});
        DataStore.updateStudentPointsTotal(new int[]{1004, 1, 10, 0, 0});
        DataStore.updateStudentPointsTotal(new int[]{1005, 1, 50, 2, 0});
        DataStore.updateStudentPointsTotal(new int[]{1006, 1, 0, 0, 8});

        for(var course: Course.values())
            courseStatisticsList.add(new CourseStatisticsRecord(course, Statistics.getNumberOfStudentsPerCourse(course),
                                                                getNumberOfSubmissions(course)));

        courseStatisticsList.forEach(System.out::println);

//        courseStatisticsList.add(new CourseStatisticsRecord(Course.valueOf("Scala"),10, 3));

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

}