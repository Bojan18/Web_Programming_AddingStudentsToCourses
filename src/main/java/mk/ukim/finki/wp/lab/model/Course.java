package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.model.enums.CourseType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue()
    private Long courseId;
    private String name;

    @Column(length = 3000)
    private String description;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Grade> gradeList;

    @Enumerated(value = EnumType.STRING)
    private CourseType courseType;

    public Course(String name, String description, List<Grade> gradeList, Teacher teacher, CourseType courseType) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.gradeList = gradeList;
        this.courseType = courseType;
    }

    public Course() {
    }
}
