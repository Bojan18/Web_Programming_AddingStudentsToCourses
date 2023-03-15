package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.enums.CourseType;

import java.time.LocalDateTime;
import java.util.List;

public interface CourseService{
    List<Student> listStudentsByCourse(Long courseId);
//    Course addStudentInCourse(String username, Long courseId);
//    Course addStudentInCourse(String username, Long courseId);
    Course addStudentInCourse(String username, Long courseId, LocalDateTime dateTime, Character grade);
    List<Course> listAll();
    Course save(String name, String description, long teacherId, CourseType courseType);
    void delete(long courseId);
    Course editCourse(String name, String description, long teacherId, long courseId);
    Course findById(long id);
    List<Grade> getGradesById(long id);
    Course findByName(String name);

    List<Course> filterByType(CourseType courseType);
}
