package mk.ukim.finki.wp.lab.repository.InMemory;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryStudentRepository {

    public List<Student> findAllStudents(){
        return DataHolder.studentList;
    }

    public List<Student> findAllByNameOrSurname(String text){
        return DataHolder.studentList
                .stream()
                .filter(i -> i.getName().contains(text) || i.getSurname().contains(text)).toList();
    }

    public void addStudent(Student s){
        DataHolder.studentList.add(s);
    }

}
