package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.service.GradeService;

public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public Character getGradeForStudentCourse(String username, Long courseId) {
        return gradeRepository.findByStudent_UsernameAndCourse_CourseId(username, courseId).getGrade();
    }
}