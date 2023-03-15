package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "student-enrollment-summary", urlPatterns = "/StudentEnrollmentSummary")
public class StudentEnrollmentSummaryServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;
    private final TeacherService teacherService;

    public StudentEnrollmentSummaryServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService, TeacherService teacherService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        long courseId = (long) req.getSession().getAttribute("courseId");
        Course c = courseService.listAll().stream().filter(i -> i.getCourseId().equals(courseId)).findFirst().orElse(null);
        //.findFirst().orElse(null)

        webContext.setVariable("grades", courseService.getGradesById(courseId));
        webContext.setVariable("teachers", teacherService.findAll());
        webContext.setVariable("course", c);
        resp.setContentType("text/html");

        springTemplateEngine.process("studentsInCourse.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/courses");
    }
}
