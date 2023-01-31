package tracker.statistics;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    @Test
    void calculateCourseRegistrationsTest() {

        List<int[]> transactionLog = new ArrayList<>();
        transactionLog.add(new int[] {1001, 1, 2, 3, 4});
        transactionLog.add(new int[] {1004, 0, 0, 1, 1});
        transactionLog.add(new int[] {1002, 0, 0, 0, 1});
        transactionLog.add(new int[] {1003, 1, 10, 0, 0});

        assertEquals(2, Statistics.calculateCourseRegistrations(1, transactionLog) );
        assertEquals(2, Statistics.calculateCourseRegistrations(2, transactionLog) );
        assertEquals(2, Statistics.calculateCourseRegistrations(3, transactionLog) );
        assertEquals(3, Statistics.calculateCourseRegistrations(4, transactionLog) );


    }

    @BeforeEach
    void setUp() {

    }

    @BeforeAll
    static void beforeAll() {

    }
}