package mk.ukim.finki.wp.lab.web.controller;

import lombok.val;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enums.CourseType;
import mk.ukim.finki.wp.lab.model.exceptions.CourseNameExistsException;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error,
                                 @RequestParam(required = false) String filterType, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasErrors", true);
            model.addAttribute("error", error);
        }
        List<Course> courses;

        if (filterType == null)
            courses = courseService.listAll();
        else
            courses = courseService.filterByType(CourseType.valueOf(filterType));

        model.addAttribute("courses", courses);
        model.addAttribute("courseTypes", CourseType.values());

        return "listCourses";
    }

    @PostMapping
    public String courseChosen(@RequestParam long courseId, HttpSession session) {
        session.setAttribute("courseId", courseId);
        return "redirect:/AddStudent";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam long teacherId,
                             @RequestParam CourseType courseType, Model model) {

        courseService.save(name, description, teacherId, courseType);

        return "redirect:/courses";
    }

    @PostMapping("/delete/{id}")
    @Transactional
    public String deleteCourse(@PathVariable Long id) {
        this.courseService.delete(id);
        return "redirect:/courses";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(Model model, @PathVariable(required = false) Long id) {
        List<Teacher> teacherList = teacherService.findAll();
        Course course = courseService.findById(id);

        if (course == null) {
            return "redirect:/courses?error=Course not found";
        }

        courseService.editCourse(course.getName(), course.getDescription(), course.getTeacher().getId(), course.getCourseId());

        model.addAttribute("course", course);
        model.addAttribute("teachers", teacherList);
        model.addAttribute("courseTypes", CourseType.values());

        return "add-course";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addNewCoursePage(Model model, @RequestParam(required = false) String error) {
        List<Teacher> teacherList = this.teacherService.findAll();
        CourseType[] typeOfCourseList = CourseType.values();
        List<CourseType> list = Arrays.asList(typeOfCourseList);

        model.addAttribute("teachers", teacherList);
        model.addAttribute("error", error);
        model.addAttribute("courseTypes", list);

        return "add-course";
    }

    @PostMapping("/filter")
    public String filterCourses(@RequestParam String courseType) {
        return "redirect:/courses?filterType=" + courseType;
    }

}
