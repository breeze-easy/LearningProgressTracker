package tracker;

import java.util.*;

public class DataStore /*implements IData*/ {
    private static Map<Integer, Integer[]> studentPointsTotal = new TreeMap<>();
    private static final java.util.Map<Integer,Student> students = new TreeMap<>();
    private static List<Integer[]> studentPointsTransactionLog; // = new ArrayList<>();

    public DataStore() {
        studentPointsTransactionLog = new ArrayList<>();
    }

    public DataStore(List<Integer[]> studPointsTransactionLog){
        studentPointsTransactionLog = studPointsTransactionLog;
        studentPointsTransactionLog.forEach(DataStore::updateStudentPointsTotal);

    }

    //@@@ private static Map<Integer, int[]> studentPointsTotal = new TreeMap<>();

    //@@@    private static void calculateStudentPointsTotal() {
    //
    //    }

    //@@@    public static Map<Integer, int[]> getStudentPointsTotal() {
    //        return studentPointsTotal;
    //    }

//    private static final java.util.Map<Integer,Course> studentCourses = new TreeMap<>();

    static void addStudent(Student student) {
        students.put(student.getStudentId(), student);
    }

    public static Map<Integer, Student> getStudents() {
        return students;
    }

    public static List<Integer[]> getStudentPointsTransactionLog() {
        return studentPointsTransactionLog;
    }

    static void addLineOfStudentPoints(Integer[] newPoints) { // // newPoints.length = 5, indexes: 0-student id, 1-4: course points in Java, DSA, Databases, Spring
        studentPointsTransactionLog.add(newPoints);
        updateStudentPointsTotal(newPoints);
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

    static void printStudentIds() {
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

    public static boolean studentExists(int studentId) {
        return students.containsKey(studentId);
    }

    public static void printStudentPointsTotal(int studentId) {
        Integer[] points = studentPointsTotal.get(studentId);

        if (points == null) {
            points = new Integer[5];
            points[0] = studentId;
        }

        System.out.printf("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n", studentId, points[1], points[2], points[3], points[4]);
    }

    public static void updateStudentPointsTotal(Integer[] newPoints) {
        int studentId = newPoints[0];
        Integer[] studentPoints = studentPointsTotal.get(studentId);

        if (studentPoints == null) {
            studentPoints = new Integer[]{0,0,0,0,0};
            studentPoints[0] = studentId;
        }

        for (int i = 1; i < studentPoints.length; i++) {
            studentPoints[i] = studentPoints[i] + newPoints[i];
        }
        studentPointsTotal.put(studentId, studentPoints);
    }

    public static Map<Integer, Integer[]> getStudentPointsTotal() {
        return studentPointsTotal;
    }

//    @Override
    public static List<Integer[]> loadData(String type) {
        List<Integer[]> list;
        switch (type){
            case "studentPointsTransactionLog" -> list = getStudentPointsTransactionLog();
            case "studentPointsTotal" -> list = flattenMapToList(getStudentPointsTotal());
            default -> throw new IllegalArgumentException("Incorrect data log type requested" + type);
        }
        return list;
    }

     static List<Integer[]> flattenMapToList(Map<Integer, Integer[]> studentPointsTotal) {
        List<Integer[]>list = new ArrayList<>();
        studentPointsTotal.forEach((key, value) -> list.add(value));
        return list;
        }
}
