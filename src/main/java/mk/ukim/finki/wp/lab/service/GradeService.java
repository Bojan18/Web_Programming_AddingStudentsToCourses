package mk.ukim.finki.wp.lab.service;

public interface GradeService {
    Character getGradeForStudentCourse(String username, Long course_id);
}
