package mk.ukim.finki.wp.lab.repository.InMemory;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enums.CourseType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
//public class InMemoryCourseRepository {
//    public List<Course> findAllCourses(){
//        return DataHolder.courseList;
//    }
//
//    public Course findById(Long courseId){
//        return DataHolder.courseList.stream().filter(i -> i.getCourseId().equals(courseId)).findFirst().orElse(null);
//    }
//
//    public List<Student> findAllStudentsByCourse(Long courseId){
//        return findById(courseId).getGradeList();
//    }
//
//    public Course addStudentToCourse(Student student, Course course){
//        List<Grade> gradeList = course.getGradeList();
//        gradeList.add(student);
//        course.setStudentList(studentList);
//
//        return course;
//    }
//
//    public Optional<Course> saveCourse(String name, String description, List<Grade> gradeList, Teacher teacher, CourseType courseType){
//        DataHolder.courseList.removeIf(i -> i.getName().equals(name));
//        Course c = new Course(name, description, gradeList, teacher, courseType);
//        DataHolder.courseList.add(c);
//
//        return Optional.of(c);
//    }
//
//    public void delete(long courseId) {
//        DataHolder.courseList.removeIf(i -> i.getCourseId().equals(courseId));
//    }
//
//}
