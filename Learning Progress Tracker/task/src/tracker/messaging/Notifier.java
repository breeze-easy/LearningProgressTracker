package tracker.messaging;

import tracker.Course;
import tracker.Student;
import tracker.DataStore;

import java.util.List;

public class Notifier {
    List<Notification> notificationList;

    public Notifier() {
        notificationList = DataStore.getNotificationList();
    }

    public static void sendNotifications() {
        int counter = 0;
        List<Notification> notificationList = DataStore.getNotificationList();
        if(notificationList.size()>0){
            for(Notification notification : notificationList){
                if(notification.wasSentBefore()){
                    continue;
                }
                Student student = DataStore.getStudent(notification.getStudentId());
                //build notification message
                String  msg = "To: " + student.getEmail() + "\n";
                msg += "Re: Your Learning Progress\n";
                msg += "Hello, " + student.getFirstName() + " " + student.getLastName() + "!";
                msg += "You have accomplished our " + Course.values()[notification.getCourseId()].name() + " course!";
                System.out.println(msg);
                notification.setWasSentBefore(true);
                if(!student.wasNotified()){
                    counter++;
                }
                student.setWasNotified(true);
            }
            System.out.println("Total " + counter + " students have been notified");
        }
    }
}
