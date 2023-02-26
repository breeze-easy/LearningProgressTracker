package tracker.messaging;

public class Notification {
    int     studentId;
    int     courseId;
    boolean wasSentBefore;

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public Notification(int studentId, int courseId) {
        this.studentId  =       studentId;
        this.courseId   =       courseId;
        this.wasSentBefore = false;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                ", wasSentBefore=" + wasSentBefore +
                '}';
    }

    public boolean wasSentBefore() {
        return wasSentBefore;
    }

    public void setWasSentBefore(boolean wasSentBefore) {
        this.wasSentBefore = wasSentBefore;
    }

}
