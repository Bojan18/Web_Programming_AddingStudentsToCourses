package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enums.CourseType;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final GradeRepository gradeRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, GradeRepository gradeRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findById(courseId).get().getGradeList().stream().map(Grade::getStudent).toList();
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId, LocalDateTime date, Character grade) {
//        Course c = courseRepository.findByCourseId(courseId);
//        Student s = studentRepository.findByUsername(username);
        Student s = studentRepository.findById(username).orElseThrow();
        Course c = courseRepository.findById(courseId).orElseThrow();

        Grade g = new Grade(grade, s, c, date);

        gradeRepository.save(g);

        return c;
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional
    public Course save(String name, String description, long teacherId, CourseType courseType) {

        this.courseRepository.deleteByName(name);

//        Course c = courseRepository.findByName(name);
//
//        this.courseRepository.deleteByCourseId(c.getCourseId());

        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
        Course course = new Course(name, description, new ArrayList<>(), teacher, courseType);

        courseRepository.save(course);

        return course;
    }

    @Override
    public Course editCourse(String name, String description, long teacherId, long courseId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();
        course.setName(name);
        course.setDescription(description);
        course.setTeacher(teacher);
        course.setCourseType(CourseType.LETEN);
        courseRepository.save(course);
        return course;
    }

    @Override
    @OnDelete(action = OnDeleteAction.CASCADE)
    public void delete(long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public Course findById(long id) {
        return courseRepository.findByCourseId(id);
    }

    @Override
    @Transactional
    public List<Grade> getGradesById(long id) {
        return this.courseRepository.findById(id).get().getGradeList();
    }

    @Override
    public Course findByName(String name) {
        return courseRepository.findByName(name);
    }

    @Override
    public List<Course> filterByType(CourseType courseType) {
        return courseRepository.findByCourseType(courseType);
    }
}
