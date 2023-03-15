package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enums.CourseType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Student> studentList = new ArrayList<>();
    public static List<Course> courseList = new ArrayList<>();
    public static List<Teacher> teacherList = new ArrayList<>();

//    @PostConstruct
//    public void init(){
//        studentList = new ArrayList<>();
//        courseList = new ArrayList<>();
//        teacherList = new ArrayList<>();
//
//        Teacher teacher1 = new Teacher("teacher1", "surname1");
//        Teacher teacher2 = new Teacher("teacher2", "surname2");
//        Teacher teacher3 = new Teacher("teacher3", "surname3");
//
//        studentList.add(new Student("bojan.dimitrovski", "bojan", "Bojan", "Dimitrovski"));
//        studentList.add(new Student("pance.pancev", "pance", "Pance", "Pancev"));
//        studentList.add(new Student("aleksandar.makedonski", "aleksandar", "Aleksandar", "Makedonski"));
//        studentList.add(new Student("angela.angelova", "angela", "Angela", "Angelova"));
//        studentList.add(new Student("filip.filipov", "filip", "Filip", "Filipov"));
//
//        courseList.add(new Course("Algorithm and Data structures", "Algorithms", new ArrayList<>(), teacher1, CourseType.ZIMSKI));
//        courseList.add(new Course( "Object-Oriented Programming", "OOP", new ArrayList<>(), teacher1, CourseType.LETEN));
//        courseList.add(new Course( "Data Science", "DS", new ArrayList<>(), teacher2, CourseType.LETEN));
//        courseList.add(new Course( "Machine Learning", "ML", new ArrayList<>(), teacher2, CourseType.ZADOLZITELEN));
//        courseList.add(new Course( "Visual Programming", "VS", new ArrayList<>(), teacher3, CourseType.IZBOREN));
//
//        teacherList.add(new Teacher("teacher1", "surname1"));
//        teacherList.add(new Teacher("teacher2", "surname2"));
//        teacherList.add(new Teacher("teacher3", "surname3"));
//
//    }

}
