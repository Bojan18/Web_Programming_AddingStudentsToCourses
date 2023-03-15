package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Grade findByStudent_UsernameAndCourse_CourseId(String username, Long courseId);
    List<Grade> findAllByTimestampBetween(LocalDateTime from, LocalDateTime to);
}
