package tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Statistics should ")
public class StatisticsTest {
    private Statistics statistics;
    private ActionClass actionClass;
    private DataStore dataStore;

    @BeforeEach
    void init(){
        statistics = new Statistics();
        actionClass = new ActionClass();
        dataStore = new DataStore();
    }

@Test
@DisplayName("display most and least popular courses")
     void displayMostPopular(){
//        actionClass.addStudents();
        dataStore.updateStudentPointsTotal(new int[]{1001, 1, 2, 3, 4});
        dataStore.updateStudentPointsTotal(new int[]{1002, 1, 2, 3, 0});
        dataStore.updateStudentPointsTotal(new int[]{1003, 1, 2, 0, 0});
        dataStore.updateStudentPointsTotal(new int[]{1004, 1, 0, 0, 0});

        Map<String, Integer> numberOfStudentsPerCourse = statistics.getNumberOfStudentsPerCourse();
        assertEquals(4, numberOfStudentsPerCourse.size() );
//        statistics.printStringIntegerMap(numberOfStudentsPerCourse);
        assertEquals(4, numberOfStudentsPerCourse.get("Java"));
        assertEquals(3, numberOfStudentsPerCourse.get("DSA"));
        assertEquals(2, numberOfStudentsPerCourse.get("Databases"));
        assertEquals(1, numberOfStudentsPerCourse.get("Spring"));
    }

    @Test
    void displayMostPopular2(){
//        actionClass.addStudents();
        dataStore.updateStudentPointsTotal(new int[]{1001, 1, 2, 3, 4});
        dataStore.updateStudentPointsTotal(new int[]{1002, 1, 2, 3, 0});
        dataStore.updateStudentPointsTotal(new int[]{1003, 1, 2, 0, 0});
        dataStore.updateStudentPointsTotal(new int[]{1004, 1, 0, 0, 0});

        assertEquals(4, Statistics.getNumberOfStudentsPerCourse(Course.Java));
        assertEquals(3, Statistics.getNumberOfStudentsPerCourse(Course.DSA));
        assertEquals(2, Statistics.getNumberOfStudentsPerCourse(Course.Databases));
        assertEquals(1, Statistics.getNumberOfStudentsPerCourse(Course.Spring));
    }


    @Test
    void getNumberOfSubmissionsPerCourse() {
        DataStore.addLineOfStudentPoints(new int[]{1001, 1,2,3,0});
        DataStore.addLineOfStudentPoints(new int[]{1001, 7,2,3,0});
        DataStore.addLineOfStudentPoints(new int[]{1002, 1,2,3,4});
        DataStore.addLineOfStudentPoints(new int[]{1001, 1,2,3,4});
        DataStore.addLineOfStudentPoints(new int[]{1001, 1,2,3,4});

        assertEquals(5, Statistics.getNumberOfSubmissionsPerCourse(Course.Java));
        assertEquals(5, Statistics.getNumberOfSubmissionsPerCourse(Course.DSA));
        assertEquals(5, Statistics.getNumberOfSubmissionsPerCourse(Course.Databases));
        assertEquals(3, Statistics.getNumberOfSubmissionsPerCourse(Course.Spring));
    }
}
