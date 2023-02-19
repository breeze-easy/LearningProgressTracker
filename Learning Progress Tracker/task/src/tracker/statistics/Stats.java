package tracker.statistics;

import org.jetbrains.annotations.NotNull;
import tracker.*;

import java.util.*;

public class Stats {
    static List<Integer[]> studentPointsTransactionLog;
    static List<Integer[]> studentPointsTotal;
    static List<CourseStatsRecord> courseStatsRecordsList;

    public Stats(@NotNull IData data) {
        studentPointsTransactionLog = data.loadData("studentPointsTransactionLog");
        studentPointsTotal = data.loadData("studentPointsTotal");
    }

    public static void runStats(){
        courseStatsRecordsList = loadCourseStatsList();
        System.out.println("Unordered CourseStatsList");
        courseStatsRecordsList.forEach(System.out::println);

        printAllStats(courseStatsRecordsList);
    }

    private static void printAllStats(List<CourseStatsRecord> courseStatsRecordsList) {
        printMostLeastPopular(courseStatsRecordsList); //most popular has the biggest number of students
        printHighestLowestStudentActivity(courseStatsRecordsList); //higher activity - bigger number of completed tasks
        printEasiestHardestCourse(courseStatsRecordsList); //easiest course has the highest average grade per assignment
        //TODO: add method to display top learners for each course.
        //need to get user input to select a course to display top learners
    }

    private static void printEasiestHardestCourse(List<CourseStatsRecord> courseStatsRecordsList) {
        String easiest = "Easiest course: ";
        String hardest = "Hardest course: ";
        if(courseStatsRecordsList == null ||courseStatsRecordsList.size()==0 ) {
            System.out.println(easiest += "n/a");
            System.out.println(hardest = "n/a");
            return;
        }

        //sort the list based on average submission grade - descending sort
        Collections.sort(courseStatsRecordsList, new Comparator<CourseStatsRecord>() {
            @Override
            public int compare(CourseStatsRecord o1, CourseStatsRecord o2) {
                return Double.compare(o2.avgSubmissionGrade(), o1.avgSubmissionGrade());
            }
        });

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
            System.out.println(highestActivity = "n/a");
            return;
        }

        //sort the list based on numberOfStudents field - descending sort
        Collections.sort(courseStatsRecordsList, new Comparator<CourseStatsRecord>() {
            @Override
            public int compare(CourseStatsRecord o1, CourseStatsRecord o2) {
                return o2.numberOfSubmissions()-o1.numberOfSubmissions();
            }
        });

        //print most popular
        int currentNumOfSubmissions = -1;

        for (int i = 0; i <courseStatsRecordsList.size(); i++) {
            currentNumOfSubmissions = courseStatsRecordsList.get(i).numberOfSubmissions();
            highestActivity += courseStatsRecordsList.get(i).course().name();

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

        System.out.println(lowestActivity);
    }

    private static void printMostLeastPopular(List<CourseStatsRecord> courseStatsRecordsList) {
        String mostPopular = "Most Popular: ";
        String leastPopular = "Least Popular: ";
        if(courseStatsRecordsList == null ||courseStatsRecordsList.size()==0 ) {
            System.out.println(mostPopular += "n/a");
            System.out.println(leastPopular = "n/a");
            return;
        }

        //sort the list based on numberOfStudents field - descending sort
        Collections.sort(courseStatsRecordsList, new Comparator<CourseStatsRecord>() {
            @Override
            public int compare(CourseStatsRecord o1, CourseStatsRecord o2) {
                return o2.numOfRegisteredStudents()-o1.numOfRegisteredStudents();
            }
        });

        //print most popular
        int currentNumOfStudents = -1;

        for (int i = 0; i <courseStatsRecordsList.size(); i++) {
            currentNumOfStudents = courseStatsRecordsList.get(i).numOfRegisteredStudents();
            mostPopular+= courseStatsRecordsList.get(i).course().name();

            if(courseStatsRecordsList.size()-1 > i){ //if there are more records
                if(currentNumOfStudents==courseStatsRecordsList.get(i+1).numOfRegisteredStudents()){
                    mostPopular += ", ";
                }else break;
            }
        }
        System.out.println(mostPopular);

        //print least popular
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

        System.out.println(leastPopular);
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
           //TODO: add List<Integer> student list to the record
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
           courseStatisticsList.add(new CourseStatsRecord(c, numOfStudents, numOfSubmissions, totalSubmissionsScore, avgGrade, studentList));
       }
        return courseStatisticsList;
    }
}
