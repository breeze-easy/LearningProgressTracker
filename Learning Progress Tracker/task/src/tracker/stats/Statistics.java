package tracker.stats;

import java.util.List;
import java.util.Map;

public class Statistics {

    List<int []> transactionLog;
    List<CourseStats> listOfCourseStats;

    class CourseStats{
        String courseName;
        int numberOfRegisteredStudents;
        int numberOfSubmissions;
        int totalSubmissionPoints;
    }

    public Statistics(List<int[]> dataLog){
        if(dataLog.get(0).length == 5) {
            transactionLog = dataLog;
        }else {
            System.out.println("Wrong arg passed to Statistics constructor.");

        }

    }

    void loadListOfCourseStats(){
        CourseStats javaStats = loadStats("java");
    }

    private CourseStats loadStats(String java) {
        return null;
    }
}
