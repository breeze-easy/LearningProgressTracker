package tracker.statistics;

import org.jetbrains.annotations.NotNull;
import tracker.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class Stats {
    static List<Integer[]> studentPointsTransactionLog;
    static List<Integer[]> studentPointsTotal;
    static List<CourseStatsRecord> courseStatsRecordsList;
    static Scanner scanner = new Scanner(System.in);

    public Stats(@NotNull DataStore data) {
        studentPointsTransactionLog = data.loadData("studentPointsTransactionLog");
        studentPointsTotal = data.loadData("studentPointsTotal");
    }

    public Stats() {
        studentPointsTransactionLog = DataStore.loadData("studentPointsTransactionLog");
        studentPointsTotal = DataStore.loadData("studentPointsTotal");
    }

    public static void runStats(){
        courseStatsRecordsList = loadCourseStatsList();
//        System.out.println("Unordered CourseStatsList");
//        courseStatsRecordsList.forEach(System.out::println);
        String userInput = "";
        while (!userInput.equals("back")) {
            System.out.println("Type the name of a course to see details or 'back' to quit");

            printAllStats(courseStatsRecordsList);
            while (!userInput.equals("back")){
//                System.out.print("> ");
                userInput = scanner.nextLine().toLowerCase();
                if(userInput.equals("back"))
                    break;

                String[] values = Arrays.stream(Course.values()).map(c -> c.name().toLowerCase()).toArray(String[]::new); // {"java", "dsa", "databases", "spring"};
                boolean contains = Arrays.asList(values).contains(userInput);

                if (Arrays.asList(values).contains(userInput))
                    printListOfStudentPointsAndCompletion(userInput, courseStatsRecordsList);
                else
                    System.out.println("Unknown course.");

            }
        }
    }

    private static void printAllStats(List<CourseStatsRecord> courseStatsRecordsList) {
        printMostLeastPopular(courseStatsRecordsList); //most popular has the biggest number of students
        printHighestLowestStudentActivity(courseStatsRecordsList); //higher activity - bigger number of completed tasks
        printEasiestHardestCourse(courseStatsRecordsList); //easiest course has the highest average grade per assignment
    }

    private static void printEasiestHardestCourse(List<CourseStatsRecord> courseStatsRecordsList) {
        String easiest = "Easiest course: ";
        String hardest = "Hardest course: ";
        if(courseStatsRecordsList == null ||courseStatsRecordsList.size()==0 ) {
            System.out.println(easiest += "n/a");
            System.out.println(hardest += "n/a");
            return;
        }

        //sort the list based on average submission grade - descending sort
        Collections.sort(courseStatsRecordsList, (o1, o2) -> Double.compare(o2.avgSubmissionGrade(), o1.avgSubmissionGrade()));

        //print easiest
        double currAvgSubmitionGrade = -1;

        for (int i = 0; i <courseStatsRecordsList.size(); i++) {
            currAvgSubmitionGrade = courseStatsRecordsList.get(i).avgSubmissionGrade();
            if(currAvgSubmitionGrade > 0){
                easiest += courseStatsRecordsList.get(i).course().name();
            }else continue;

            if(courseStatsRecordsList.size()-1 > i){ //if there are more records
                if(currAvgSubmitionGrade == courseStatsRecordsList.get(i+1).avgSubmissionGrade()){
                    easiest += ", ";
                }else break;
            }
        }
        System.out.println(easiest);

        //print hardest
        currAvgSubmitionGrade=-1;
        for (int i = courseStatsRecordsList.size()-1; i>=0; i--) {
            currAvgSubmitionGrade = courseStatsRecordsList.get(i).avgSubmissionGrade();
            if(currAvgSubmitionGrade > 0){
                hardest += courseStatsRecordsList.get(i).course().name();
            }else continue;

            if(i>0){ //if there are more records
                if(currAvgSubmitionGrade == courseStatsRecordsList.get(i-1).avgSubmissionGrade()){
                    hardest += ", ";
                }else break;
            }

        }

        System.out.println(hardest);
    }

    private static void printHighestLowestStudentActivity(List<CourseStatsRecord> courseStatsRecordsList) {
        String highestActivity = "Highest Activity: ";
        String lowestActivity = "Lowest activity: ";
        if(courseStatsRecordsList == null ||courseStatsRecordsList.size()==0 ) {
            System.out.println(highestActivity += "n/a");
            System.out.println(lowestActivity += "n/a");
            return;
        }

        //sort the list based on numberOfStudents field - descending sort
        Collections.sort(courseStatsRecordsList, (o1, o2) -> o2.numberOfSubmissions()-o1.numberOfSubmissions());

        //print highest activity
        int numOfHighestActivity = 0;
        int currentNumOfSubmissions = -1;

        for (int i = 0; i <courseStatsRecordsList.size(); i++) {
            currentNumOfSubmissions = courseStatsRecordsList.get(i).numberOfSubmissions();
            highestActivity += courseStatsRecordsList.get(i).course().name();
            numOfHighestActivity++;

            if(courseStatsRecordsList.size()-1 > i){ //if there are more records
                if(currentNumOfSubmissions==courseStatsRecordsList.get(i+1).numberOfSubmissions()){
                    highestActivity += ", ";
                }else break;
            }
        }
        System.out.println(highestActivity);

        //print least popular
        currentNumOfSubmissions=-1;
        for (int i = courseStatsRecordsList.size()-1; i>=0; i--) {
            currentNumOfSubmissions = courseStatsRecordsList.get(i).numOfRegisteredStudents();
            lowestActivity += courseStatsRecordsList.get(i).course().name();

            if(i>0){ //if there are more records
                if(currentNumOfSubmissions == courseStatsRecordsList.get(i-1).numOfRegisteredStudents()){
                    lowestActivity += ", ";
                }else break;
            }

        }

        if (numOfHighestActivity==courseStatsRecordsList.size()){
            System.out.println("n/a");
        }else {
            System.out.println(lowestActivity);
        }
    }

    private static void printMostLeastPopular(List<CourseStatsRecord> courseStatsRecordsList) {
        String mostPopular = "Most Popular: ";
        String leastPopular = "Least Popular: ";
        if(courseStatsRecordsList == null ||courseStatsRecordsList.size()==0 ) {
            System.out.println(mostPopular += "n/a");
            System.out.println(leastPopular += "n/a");
            return;
        }

        //sort the list based on numberOfStudents field - descending sort
        Collections.sort(courseStatsRecordsList, (o1, o2) -> o2.numOfRegisteredStudents()-o1.numOfRegisteredStudents());

        //print most popular
        int currentNumOfStudents = -1;
        int numOfMostPopular = 0; //how many courses from the list have same number of students

        for (int i = 0; i <courseStatsRecordsList.size(); i++) {
            currentNumOfStudents = courseStatsRecordsList.get(i).numOfRegisteredStudents();
            mostPopular+= courseStatsRecordsList.get(i).course().name();
            numOfMostPopular++;

            if(courseStatsRecordsList.size()-1 > i){ //if there are more records
                if(currentNumOfStudents==courseStatsRecordsList.get(i+1).numOfRegisteredStudents()){
                    mostPopular += ", ";
                }else break;
            }
        }
        System.out.println(mostPopular);

        //print least popular
        int numOfLeastPopular = 0;
        currentNumOfStudents=-1;
        for (int i = courseStatsRecordsList.size()-1; i>=0; i--) {
            currentNumOfStudents = courseStatsRecordsList.get(i).numOfRegisteredStudents();
            leastPopular+= courseStatsRecordsList.get(i).course().name();

            if(i>0){ //if there are more records
                if(currentNumOfStudents==courseStatsRecordsList.get(i-1).numOfRegisteredStudents()){
                    leastPopular += ", ";
                }else break;
            }

        }

        if(numOfMostPopular == courseStatsRecordsList.size()){
            System.out.println("n/a");
        }else {
            System.out.println(leastPopular);
        }
    }

    private static List<CourseStatsRecord> loadCourseStatsList() {
        List<CourseStatsRecord> courseStatisticsList = new ArrayList<>();
        int numOfStudents;
        int numOfSubmissions;
        int totalSubmissionsScore;
        double avgGrade;
        List<Integer[]> studentList = new ArrayList<>();

        for(Course c : Course.values()){
            int id = 0; //student id
            int courseIdx = Course.indexOfCourseInArray(c);

            numOfStudents = (int) studentPointsTotal.stream().map(x -> x[courseIdx]).filter(x -> x > 0).count();
            numOfSubmissions = (int) studentPointsTransactionLog.stream().map(x -> x[courseIdx]).filter(x -> x > 0).count();
            totalSubmissionsScore = studentPointsTotal.stream().map(x -> x[courseIdx]).reduce(0, Integer::sum);
            avgGrade =  Math.round((double) totalSubmissionsScore / numOfSubmissions * 100d) / 100d;
            studentList =  studentPointsTotal.stream()
                    .map(x -> (new Integer[] {x[id], x[courseIdx]}))
                    .filter(x-> x[1] > 0)
                    .toList();
/*
           //sort student list based on 2 criteria: 1-score(desc), 2-id(asc)
           Comparator<Integer[]> compScores = new Comparator<Integer[]>() {
               @Override
               public int compare(Integer[] o1, Integer[] o2) {
                   return o2[1] - o1[1];
               }
           };
           Comparator<Integer[]> compIds = new Comparator<Integer[]>() {
               @Override
               public int compare(Integer[] o1, Integer[] o2) {
                   return o1[0]-o2[0];
               }
           };
*/

 /*          System.out.println("List for " + c.name());
           studentList.forEach(x->
            {
                double percentOfCompletion = Math.round((double) x[1] / c.getPointsToComplete()*100d) / 100d;
                String s = Arrays.toString(x) + "\t" + percentOfCompletion * 100 + "%";
               System.out.println(s);
           });
           System.out.println();*/
            if(numOfStudents == 0) continue; // if no students - don't add this course record to the list

            courseStatisticsList.add(new CourseStatsRecord(c, numOfStudents, numOfSubmissions, totalSubmissionsScore, avgGrade, studentList));
        }
        return courseStatisticsList;
    }

    private static boolean showCourseDetailsAndExit(String input) {
        boolean result;
        input = input.toLowerCase();

        while (true) {
            if (input.equals("back")) {
//                System.out.print("> ");
                result = true;
                break;
            }

            String[] values = {"java", "dsa", "databases", "spring"};
            boolean contains = Arrays.asList(values).contains(input);

            if (contains) {
                //System.out.println("Calling printListOfStudentPointsAndCompletion("+input+")");
                String courseName = input.substring(0, 1).toUpperCase() + input.substring(1);
                printListOfStudentPointsAndCompletion(courseName, courseStatsRecordsList);
            } else System.out.println("Unknown course.");
            input = scanner.nextLine();
        }
        return result;
    }

    private static void printListOfStudentPointsAndCompletion(String courseName, List<CourseStatsRecord> courseStatsList) {
        String realCourseName = courseName.substring(0, 1).toUpperCase() + courseName.substring(1); //capitalize 1st letter
        //print header
        System.out.println(realCourseName);
        System.out.println("id\t\tpoints\tcompleted");

        if(courseStatsList.size()==0){
//            System.out.println("Error: Course statistics Record List is empty");
            return;
        }
        //get list of student points
        List<Integer[]> studPointsList = courseStatsList.stream()
                .filter(x -> x.course().name().toLowerCase().equals(courseName))
                .findFirst().get().listOfStudentScores();
//        studPointsList.forEach(x-> System.out.println(Arrays.toString(x)));
        List<Integer[]> modList = new ArrayList<>(studPointsList);
        //sort the list
        // Define a custom comparator that sorts based on two elements - 1st desc sort in idx1, then acs sort on idx0
        Comparator<Integer[]> studentScoreComparator = (o1, o2) -> {
            int compareFirst = Integer.compare(o2[1], o1[1]);
            if (compareFirst != 0) {
                return compareFirst;
            } else {
                return Integer.compare(o1[0], o2[0]);
            }
        };
        Collections.sort(modList,studentScoreComparator);
//        System.out.println("Sorted list:");

        if(realCourseName.equals("Dsa")) realCourseName = realCourseName.toUpperCase();
        Course course = Course.valueOf(realCourseName);


        DecimalFormat df = new DecimalFormat("0.0");

        modList.forEach(x-> {
            double percentOfCompletion = Math.round((double) x[1]/course.getPointsToComplete()*100*100d)/100d;
            System.out.println(x[0] + "\t" + x[1] + "\t\t" +
                    new BigDecimal(percentOfCompletion).setScale(1, RoundingMode.HALF_UP).doubleValue() + " %");
        });
    }
}
