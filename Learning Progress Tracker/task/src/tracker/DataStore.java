package tracker;

import tracker.messaging.Notification;

import java.util.*;


public class DataStore /*implements IData*/ {
    private static Map<Integer, Integer[]> studentPointsTotal = new TreeMap<>();
    private static final java.util.Map<Integer,Student> students = new TreeMap<>();
    private static List<Integer[]> studentPointsTransactionLog; // = new ArrayList<>();
    private static List<Notification> notificationList = new ArrayList<>();

    public DataStore() {
        studentPointsTransactionLog = new ArrayList<>();
    }

    public DataStore(List<Integer[]> studPointsTransactionLog){
        studentPointsTransactionLog = studPointsTransactionLog;
        studentPointsTransactionLog.forEach(DataStore::updateStudentPointsTotal);
    }

    public boolean addNotification(Notification notification){
        if(!notificationExists(notification.getStudentId(),notification.getCourseId())) {
            notificationList.add(notification);
            return true;
        }
        return false;
    }

    public boolean addNotification(int studentId, int courseId) {
        boolean result = false;
        try{
            if(!notificationExists(studentId,courseId,notificationList)) {
                notificationList.add(new Notification(studentId, courseId));
                result = true;
            }
        }catch (Exception e) {
            System.out.println("Error adding new notification");
            e.printStackTrace();
        }
        return result;
    }

    private boolean notificationExists(int studentId, int courseId, List<Notification> notificationList) {
        for(Notification notification : notificationList){
            if(notification.getStudentId()==studentId && notification.getCourseId()==courseId){
                return true;
            }
        }
        return false;
    }

    private static boolean notificationExists(int studentId, int courseId) {
        for(Notification notification : notificationList){
            if(notification.getStudentId()==studentId && notification.getCourseId()==courseId){
                return true;
            }
        }
        return false;
    }

    static void addStudent(Student student) {
        students.put(student.getStudentId(), student);
    }

    public static Student getStudent(Integer id){
        Student student =null;
        try{
            student = students.get(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return student;
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
        generateNotificationList((flattenMapToList(studentPointsTotal)), notificationList);

        System.out.println("Points updated.");
    }

    private static void generateNotificationList(List<Integer[]> studentPointsTotal , List<Notification> notificationList) {
            Course[] courses = Course.values();
            for (Integer[] line : studentPointsTotal){
                for (int i = 1; i < line.length; i++) {
                    int studentId = line[0];
                    int courseId = i-1; // in studentPointsTotal array course idx 1-4
                    Course course = courses[courseId]; // in Course[] course idx 0-3. Java:0, DSA:1, Databases:2, Spring:3
                    if(line[i] >= courses[i-1].getPointsToComplete() && !notificationExists(studentId, courseId)){
                        notificationList.add(new Notification(studentId,courseId));
                    }
                }
            }
            System.out.println("Notifications: ");
        notificationList.forEach(x-> System.out.println(x.toString()));
    }

    private static boolean notificationExists(Integer[] newPoints, List<Notification> notificationList) {
        for (Notification notification : notificationList){
            for (int i = 1; i < newPoints.length; i++) { //i=0: studId; i{1-4}: courseId
                if(notification.getStudentId()== newPoints[0] && notification.getCourseId()== newPoints[i]){
                    return true;
                }
            }
        }
        return false;
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

    public static List<Notification> getNotificationList() {
        return notificationList;
    }
}
