package tracker;

public class Course {
    private final String courseName;
    private final int pointsToComplete;


    public Course(String name) {
        this.courseName = name;
        switch (name) {
            case "Java":
                pointsToComplete = 600;
                break;
            case "DSA":
                pointsToComplete = 400;
                break;
            case "Databases":
                pointsToComplete = 480;
                break;
            case "Spring":
                pointsToComplete = 550;
                break;
            default:
                pointsToComplete = -1;
                System.out.println("You've entered incorrect course name");
        }
    }

    public String getCourseName() {
        return courseName;
    }

    public int getPointsToComplete() {
        return pointsToComplete;
    }
}
