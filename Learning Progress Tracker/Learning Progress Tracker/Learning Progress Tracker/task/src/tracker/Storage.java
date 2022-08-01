package tracker;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<Student> students;

    Storage(){
        this.students = new ArrayList<>();
    }

    public void saveStudents(List<Student> newStudents){
        this.students.addAll(newStudents);
    }
}