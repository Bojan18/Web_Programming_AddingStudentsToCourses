package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "list-students", urlPatterns = "/AddStudent")
public class ListStudentServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;
    private final CourseService courseService;

    public ListStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        long courseId = (long) req.getSession().getAttribute("courseId");
        webContext.setVariable("courseId", courseId);
        webContext.setVariable("students", studentService.listAll());
        resp.setContentType("text/html");

        springTemplateEngine.process("listStudents.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String student = req.getParameter("student");
        req.getSession().setAttribute("student", student);
        long courseId = (long) req.getSession().getAttribute("courseId");
//        req.getSession().setAttribute("courseId", courseId);

        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        Character grade = req.getParameter("grade").charAt(0);
        courseService.addStudentInCourse(student, courseId, dateTime, grade);

        resp.sendRedirect("/StudentEnrollmentSummary");
    }
}
