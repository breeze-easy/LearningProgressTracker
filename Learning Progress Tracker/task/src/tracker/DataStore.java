package tracker;

import java.util.*;

public class DataStore {
    private static final java.util.Map<Integer,Student> students = new TreeMap<>();
    private static List<int[]> studentPointsTransactionLog = new ArrayList<>();
    private static Map<Integer, int[]> studentPointsTotal = new TreeMap<>();

    private static void calculateStudentPointsTotal() {

    }

    public static Map<Integer, int[]> getStudentPointsTotal() {
        return studentPointsTotal;
    }

//    private static final java.util.Map<Integer,Course> studentCourses = new TreeMap<>();

    static void addStudent(Student student) {
        students.put(student.getStudentId(), student);
    }

    public static Map<Integer, Student> getStudents() {
        return students;
    }

    public static List<int[]> getStudentPointsTransactionLog() {
        return studentPointsTransactionLog;
    }

    void addLineOfStudentPoints(int[] newPoints) { // // newPoints.length = 5, indexes: 0-student id, 1-4: course points in Java, DSA, Databases, Spring
        studentPointsTransactionLog.add(newPoints);
        System.out.println("Points updated.");
    }

    static boolean studentAlreadyExists(String email) {
        Set<Integer> keys = students.keySet();
        for (Integer key : keys) {
            Student student = students.get(key);
            if (student.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    void printStudentIds() {
        Set<Integer> listIds = students.keySet();
        if (!listIds.isEmpty()) {
            System.out.println("Students:");
            for (Integer key : listIds) {
                System.out.println(key);
            }
        } else {
            System.out.println("No students found");
        }
    }

    public boolean studentExists(int studentId) {
        return students.containsKey(studentId);
    }

    public void printStudentPointsTotal(int studentId) {
        int[] points = studentPointsTotal.get(studentId);

        if (points == null) {
            points = new int[5];
            points[0] = studentId;
        }

        System.out.printf("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n", studentId, points[1], points[2], points[3], points[4]);
    }

    public void updateStudentPointsTotal(int[] newPoints) {
        int studentId = newPoints[0];
        int[] studentPoints = studentPointsTotal.get(studentId);

        if (studentPoints == null) {
            studentPoints = new int[5];
            studentPoints[0] = studentId;
        }

        for (int i = 1; i < studentPoints.length; i++) {
            studentPoints[i] = studentPoints[i] + newPoints[i];
        }
        studentPointsTotal.put(studentId, studentPoints);
    }
}