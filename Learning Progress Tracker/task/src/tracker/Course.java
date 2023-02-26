package tracker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public enum Course {
    Java(600), DSA(400), Databases(480), Spring(550);

    public int pointsToComplete; // points to complete each course
    Course(int i) {pointsToComplete = i;}

    // maps course name to corresponding course index in data arrays: transactionLog, etc.
    public static int indexOfCourseInArray(Course course) {
        return switch (course){
            case Java -> 1;
            case DSA -> 2;
            case Databases -> 3;
            case Spring -> 4;
        };
    }

    public int getPointsToComplete() {return pointsToComplete;}
}

class CourseDemo {
    public static void main(String[] args) {
//        System.out.println(Course.Java);
//
//        System.out.println("All courses and points to complete:");
//        for (var c : Course.values()) {
//            System.out.printf("%s, points to complete: %d. Ordinal position: %d%n", c.name(), c.getPointsToComplete(), c.ordinal());
//        }

        String[] arrCourses = Arrays.stream(Course.values()).map(c -> c.name()).toArray(String[]::new);
        Arrays.stream(arrCourses).forEach(System.out::println);


    }
}

