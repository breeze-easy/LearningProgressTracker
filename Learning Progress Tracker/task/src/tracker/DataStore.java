package tracker;

import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

public class DataStore {
    private static final java.util.Map<Integer,Student> students = new TreeMap<>();

    static void addStudent(Student student) {
        students.put(student.getStudentId(), student);
    }

    void updateStudentScores(int[] newScores) { // newScores.length = 5
        if (newScores == null || newScores.length != 5) {
            System.out.println("Incorrect points format.");
            return;
        }
        Student student = students.get(newScores[0]); // first item is a studentId

        int[] oldScores = student.getTestScores(); // length = 4
        if (oldScores == null) oldScores = new int[4];
        for (int i = 0; i < oldScores.length; i++) {
            if (newScores[i+1] <= 0) continue;
            oldScores[i] += newScores[i + 1];
        }
        student.setTestScores(oldScores);
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

    void listStudentIds() {
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
        boolean result;
        if (students.containsKey(studentId)) {
            result = true;
        } else {
            result = false;
//            System.out.printf("No student is found for id=%d.\n", studentId);
        }
        return result;
    }

    public void getStudentPoints(int studentId) {
        Student student = students.get(studentId);
        if (student != null) {
            int[]points = student.getTestScores();
            System.out.printf("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n", studentId, points[0], points[1], points[2], points[3]);
        } else {
            System.out.printf("No student is found for id=%d\n", studentId);
        }
    }
}