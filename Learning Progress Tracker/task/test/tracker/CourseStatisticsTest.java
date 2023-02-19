package tracker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CourseStatisticsTest {



    @Test
    void listOfStudentsWithScores() {
        List<Integer[]> studentTransactionLog = new ArrayList<>();

        studentTransactionLog.add(new Integer[] {1001, 8, 0, 10, 0});
        studentTransactionLog.add(new Integer[] {1001, 2, 0, 0, 0});
        studentTransactionLog.add(new Integer[] {1002, 1, 1, 0, 0});
        studentTransactionLog.add(new Integer[] {1003, 7, 2, 1, 0});
        studentTransactionLog.add(new Integer[] {1004, 4, 2, 1, 1});

        DataStore dataStore = new DataStore(studentTransactionLog);
//        Map<Integer,int[]> studentPointsTotal= DataStore.getStudentPointsTotal();

        CourseStatistics javaCourseStats = new CourseStatistics("java");
        CourseStatistics dsaCourseStats = new CourseStatistics("dsa");
        CourseStatistics dbCourseStats = new CourseStatistics("databases");
        CourseStatistics springCourseStats = new CourseStatistics("spring");


    }
}