package tracker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CourseStatisticsTest {

    @Test
    void getNumberOfRegisteredStudents() {
        // populate DataStore transaction log
        DataStore dataStore = new DataStore();
        dataStore.updateStudentPointsTotal(new int[] {1001, 1,0,0,0});
        dataStore.updateStudentPointsTotal(new int[] {1002, 1,1,0,0});
        dataStore.updateStudentPointsTotal(new int[] {1003, 1,2,1,0});
        dataStore.updateStudentPointsTotal(new int[] {1004, 1,2,1,1});

        CourseStatistics javaCourseStats = new CourseStatistics("java");
        CourseStatistics dsaCourseStats = new CourseStatistics("dsa");
        CourseStatistics dbCourseStats = new CourseStatistics("databases");
        CourseStatistics springCourseStats = new CourseStatistics("spring");

        assertEquals(4, javaCourseStats.getNumberOfRegisteredStudents());
        assertEquals(3, dsaCourseStats.getNumberOfRegisteredStudents());
        assertEquals(2, dbCourseStats.getNumberOfRegisteredStudents());
        assertEquals(1, springCourseStats.getNumberOfRegisteredStudents());
    }

    @Test
    void getNumberOfSubmissions() {
        List<int[]> studentTransactionLog = new ArrayList<>();

        studentTransactionLog.add(new int[] {1001, 1, 0, 10, 0});
        studentTransactionLog.add(new int[] {1001, 1, 0, 0, 0});
        studentTransactionLog.add(new int[] {1002, 1, 1, 0, 0});
        studentTransactionLog.add(new int[] {1003, 1, 2, 1, 0});
        studentTransactionLog.add(new int[] {1004, 1, 2, 1, 1});

        DataStore dataStore = new DataStore(studentTransactionLog);

        CourseStatistics javaCourseStats = new CourseStatistics("java");
        CourseStatistics dsaCourseStats = new CourseStatistics("dsa");
        CourseStatistics dbCourseStats = new CourseStatistics("databases");
        CourseStatistics springCourseStats = new CourseStatistics("spring");

        assertEquals(5, javaCourseStats.getNumberOfSubmissions());
        assertEquals(3, dsaCourseStats.getNumberOfSubmissions());
        assertEquals(3, dbCourseStats.getNumberOfSubmissions());
        assertEquals(1, springCourseStats.getNumberOfSubmissions());
    }

    @Test
    void getTotalOfAllSubmissions() {
        List<int[]> studentTransactionLog = new ArrayList<>();

        studentTransactionLog.add(new int[] {1001, 2, 0, 10, 0});
        studentTransactionLog.add(new int[] {1001, 1, 0, 0, 0});
        studentTransactionLog.add(new int[] {1002, 1, 1, 0, 0});
        studentTransactionLog.add(new int[] {1003, 1, 2, 1, 0});
        studentTransactionLog.add(new int[] {1004, 1, 2, 1, 1});

        DataStore dataStore = new DataStore(studentTransactionLog);

        CourseStatistics javaCourseStats = new CourseStatistics("java");
        CourseStatistics dsaCourseStats = new CourseStatistics("dsa");
        CourseStatistics dbCourseStats = new CourseStatistics("databases");
        CourseStatistics springCourseStats = new CourseStatistics("spring");

        assertEquals(6, javaCourseStats.getTotalOfAllSubmissions());
        assertEquals(5, dsaCourseStats.getTotalOfAllSubmissions());
        assertEquals(12, dbCourseStats.getTotalOfAllSubmissions());
        assertEquals(1, springCourseStats.getTotalOfAllSubmissions());
    }

    @Test
    void listOfStudentsWithScores() {
        List<int[]> studentTransactionLog = new ArrayList<>();

        studentTransactionLog.add(new int[] {1001, 8, 0, 10, 0});
        studentTransactionLog.add(new int[] {1001, 2, 0, 0, 0});
        studentTransactionLog.add(new int[] {1002, 1, 1, 0, 0});
        studentTransactionLog.add(new int[] {1003, 7, 2, 1, 0});
        studentTransactionLog.add(new int[] {1004, 4, 2, 1, 1});

        DataStore dataStore = new DataStore(studentTransactionLog);
//        Map<Integer,int[]> studentPointsTotal= DataStore.getStudentPointsTotal();

        CourseStatistics javaCourseStats = new CourseStatistics("java");
        CourseStatistics dsaCourseStats = new CourseStatistics("dsa");
        CourseStatistics dbCourseStats = new CourseStatistics("databases");
        CourseStatistics springCourseStats = new CourseStatistics("spring");

        
    }
}