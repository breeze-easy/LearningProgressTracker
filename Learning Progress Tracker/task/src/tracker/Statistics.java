package tracker;

import java.util.*;

public class Statistics {

    private static Map<String, Integer> mapNumberOfStudentsPerCourse;

    // key: course name
    // value: int[2] where 1st value is total number of points in submissions for the course
    //                     2nd value is total number of submissions for this course
    private static Map<String, int[]> mapSubmissionsPerCourse;

    // key: course name
    // value: StudentScore object (from inner class)
    private static Map<String, ArrayList<StudentScore>> mapListOfStudentsPerCourse = new HashMap<>(4);


    Scanner scanner = new Scanner(System.in);


    public Statistics() {
        //        initMapNumberOfStudentsPerCourse();
    }

    private static void initMapNumberOfStudentsPerCourse() {
        mapNumberOfStudentsPerCourse = new HashMap<>(4);

        mapNumberOfStudentsPerCourse.put("Java", 0);
        mapNumberOfStudentsPerCourse.put("DSA", 0);
        mapNumberOfStudentsPerCourse.put("Databases", 0);
        mapNumberOfStudentsPerCourse.put("Spring", 0);
    }

    private static void initMapSubmissionsPerCourse() {
        mapSubmissionsPerCourse = new HashMap<>(4);

        mapSubmissionsPerCourse.put("Java", new int[2]);
        mapSubmissionsPerCourse.put("DSA", new int[2]);
        mapSubmissionsPerCourse.put("Databases", new int[2]);
        mapSubmissionsPerCourse.put("Spring", new int[2]);
    }


    public void runStatistics() {
        String input;

        while (true){
            System.out.println("Type the name of a course to see details or 'back' to quit");
            showMostLeastPopular();
            showHighestLowestActivity();
            showEasiestHardest();

            System.out.print("> ");
            input = scanner.nextLine();
            if (input.equals("back")) {
                break;
            }
            if (showCourseDetailsAndExit(input)) break;
        }
    }

    private boolean showCourseDetailsAndExit(String input) {
        boolean result;
        while (true) {
            if (input.equals("back")) {
//                System.out.print("> ");
                result = true;
                break;
            }

            String[] values = {"java","dsa","databases","spring"};
            boolean contains = Arrays.asList(values).contains(input);

            if (contains) {
                //System.out.println("Calling printListOfStudentPointsAndCompletion("+input+")");
                String courseName = input.substring(0, 1).toUpperCase() + input.substring(1);
                printListOfStudentPointsAndCompletion(courseName);
            } else System.out.println("Unknown course.");
            input = scanner.nextLine();
        }
        return result;
    }

    private void printListOfStudentPointsAndCompletion(String courseName) {
        //print header
        System.out.println(courseName);
        System.out.println("id\t\tpoints\t  completed");
        //TODO: finish coding method - print sorted list of students and % of completion
        //TODO: remove dummy code
        System.out.println("1001\t  24\t\t4.0%");
        System.out.println("1002\t  21\t\t3.5%");
        System.out.print("> ");
    }

    private void showHighestLowestActivity() {
        System.out.println("Highest activity:\t");
        System.out.println("Lowest activity:\t");
    }

    private void showEasiestHardest() {
        System.out.println("Easiest course:\t");
        System.out.println("Hardest course:\t");
    }

    private void showMostLeastPopular() {
        mapNumberOfStudentsPerCourse = getNumberOfStudentsPerCourse();
        System.out.println("Most popular:\t");
        System.out.println("Least popular\t");
        printStringIntegerMap(mapNumberOfStudentsPerCourse);
    }

    private void printStringIntegerMap(Map<String, Integer> map) {
        map.forEach(((key, value) -> System.out.println(key + ":" + value)));
    }

    private static void printMap(Map<Object, Object> map) {
        map.forEach((key, value) -> System.out.println(key + ":" + value));
    }

    // get count for students that have points for each course
    Map<String, Integer> getNumberOfStudentsPerCourse() {
        initMapNumberOfStudentsPerCourse();
        DataStore.getStudentPointsTotal().forEach((key, value1) -> {
            for (int i = 1; i < 5; i++) { // indexes of array - 0: studId, 1: Java points, 2: DSA points, 3: Databases
                                          //                    4: Spring points
                String courseName = getCourseName(i);
                int value = value1[i];
                if (value > 0) {
                    mapNumberOfStudentsPerCourse.put(courseName, mapNumberOfStudentsPerCourse.get(courseName) + 1);
                }
            }
        });
//         Collections.sort();
        return mapNumberOfStudentsPerCourse;
    }

    Map<String, Integer> getMapSubmissionsPerCourse() {
        initMapSubmissionsPerCourse();
        DataStore.getStudentPointsTransactionLog().stream().map(e -> {

            return null;
        });

        return null;
    }

    private String getCourseName(int i) {
        String courseName = switch (i) {
            case 1 -> "Java";
            case 2 -> "DSA";
            case 3 -> "Databases";
            case 4 -> "Spring";
            default -> throw new IllegalStateException("Unexpected value: " + i);
        };
        return courseName;
    }

    private class StudentScore {
        int studentId;
        int score;
        float completion;


        public StudentScore(int studentId) {
            this.studentId = studentId;
        }
    }
}


