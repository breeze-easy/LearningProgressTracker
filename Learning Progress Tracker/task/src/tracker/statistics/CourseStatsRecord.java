package tracker.statistics;

import tracker.Course;

import java.util.List;

//TODO: add List<Integer[2]> listOfStudentScores - 1st element: StudentId, 2nd: Student Score for the course
public record CourseStatsRecord(Course course, int numOfRegisteredStudents, int numberOfSubmissions
        , int totalSubmissionsScore, double avgSubmissionGrade, List<Integer[]> listOfStudentScores) {
}
