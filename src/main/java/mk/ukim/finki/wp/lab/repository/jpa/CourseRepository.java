package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.enums.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    void deleteByCourseId(Long id);
    Course findByCourseId(Long id);
    Course findByName(String name);
    void deleteByName(String name);
    List<Course> findByCourseType(CourseType courseType);
}
