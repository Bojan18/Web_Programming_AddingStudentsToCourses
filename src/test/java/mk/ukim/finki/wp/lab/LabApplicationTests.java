package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enums.CourseType;
import mk.ukim.finki.wp.lab.model.enums.Role;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import mk.ukim.finki.wp.lab.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.regex.Matcher;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LabApplicationTests {

    MockMvc mockMvc;

    @Autowired
    TeacherService teacherService;

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    private static TeacherService t1;
    private static boolean dataInitialized = false;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    //pred sekoj test ke se izvrsi ovoj metod
    public void setup(WebApplicationContext wac){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        initData();
    }

//    private void initData(){
//        //ke go iskoristime za init na nasi podatoci
//        //kreirame korisnici, produkt, bla bla
//        if(!dataInitialized){
//            t1 = (TeacherService) teacherService.create("name1", "surname1", LocalDate.of(2020, Month.JANUARY, 8));
//            teacherService.create("name2", "surname2", LocalDate.of(2020, Month.JANUARY, 8));
////            courseService.save("course1", "desc1", t1, CourseType.LETEN);
//
//            String user = "user";
//            String admin = "admin";
//            userService.register(user, user, user, user, user, Role.ROLE_USER);
//            userService.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
//
//
//        }
//    }

//    @Test
//    public void testDeleteCourse() throws Exception {
////        Product product = this.productService.save("test", 2.0, 2, c1.getId(), m1.getId()).get();
//        Course course = this.courseService.save("name1", "desc1", t1.findAll().get(0).getId(), CourseType.LETEN);
//        MockHttpServletRequestBuilder courseDeleteRequest = MockMvcRequestBuilders
//                .delete("/courses/delete/" + course.getCourseId());
//
//        this.mockMvc.perform(courseDeleteRequest)
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/courses"));
//    }


    private void initData(){
        String user = "user";
        userService.register(user, user, user, user, user, Role.ROLE_USER);
        String admin = "admin";
        userService.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
    }


    @Test
    void contextLoads() {
    }

    @Test
    public void testGetCourses() throws Exception {
        MockHttpServletRequestBuilder productRequest = MockMvcRequestBuilders.get("/courses");
        this.mockMvc.perform(productRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
//        model.addAttribute("courses", courses);
//        model.addAttribute("courseTypes", CourseType.values());
                .andExpect(MockMvcResultMatchers.model().attributeExists("courses"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("courseTypes"))
//        return "listCourses";
                .andExpect(MockMvcResultMatchers.view().name("listCourses"));
    }

    @Test
    void testGetStudents() throws Exception{
        MockHttpServletRequestBuilder productRequest = MockMvcRequestBuilders.get("/AddStudent");

        LocalDate date = LocalDate.of(2020, 1, 8);
        Teacher teacher = this.teacherService.create("n", "s", date);
        Course course = this.courseService.save("test", "desc", teacher.getId(), mk.ukim.finki.wp.lab.model.enums.CourseType.LETEN);

//        MockMvcResultMatchers.model().attribute("courseId") = course.getCourseId();

        this.mockMvc.perform(productRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.model().attributeExists("courseId"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("students"))
//                .andExpect(MockMvcResultMatchers.view().name("listStudents"));
    }

    @Test
    void testGetEditForm() throws Exception{
        LocalDate date = LocalDate.of(2020, 1, 8);
        Teacher teacher = this.teacherService.create("n", "s", date);
        Course course = this.courseService.save("test", "desc", teacher.getId(), mk.ukim.finki.wp.lab.model.enums.CourseType.LETEN);


        MockHttpServletRequestBuilder editCourse = MockMvcRequestBuilders.get("/courses/edit/" + course.getCourseId());

//        model.addAttribute("course", course);
//        model.addAttribute("teachers", teacherList);
//        model.addAttribute("courseTypes", CourseType.values());


        this.mockMvc.perform(editCourse)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.model().attributeExists("course"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("teachers"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("courseTypes"));
    }

    @Test
    public void testDeleteCourse() throws Exception {
        LocalDate date = LocalDate.of(2020, 1, 8);
        Teacher teacher = this.teacherService.create("n", "s", date);
        Course course = this.courseService.save("test", "desc", teacher.getId(), mk.ukim.finki.wp.lab.model.enums.CourseType.LETEN);
        MockHttpServletRequestBuilder courseDelete = MockMvcRequestBuilders
                .post("/courses/delete/" + course.getCourseId());

        this.mockMvc.perform(courseDelete)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/courses"));
    }
}
