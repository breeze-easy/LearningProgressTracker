package tracker.statistics;

import tracker.Course;

import java.util.List;

public record CourseStatsRecord(Course course, int numOfRegisteredStudents, int numberOfSubmissions
        , int totalSubmissionsScore, double avgSubmissionGrade, List<Integer[]> listOfStudentScores) {
}
