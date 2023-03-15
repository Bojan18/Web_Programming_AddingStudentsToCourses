package mk.ukim.finki.wp.lab.selenium;

import mk.ukim.finki.wp.lab.model.Users;
import mk.ukim.finki.wp.lab.model.enums.CourseType;
import mk.ukim.finki.wp.lab.model.enums.Role;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import mk.ukim.finki.wp.lab.service.UserService;
//import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.Month;

import mk.ukim.finki.wp.lab.model.*;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {
    @Autowired
    TeacherService teacherService;

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;
    private HtmlUnitDriver driver;

    private static TeacherService t1;
    private static Users regularUser;
    private static Users adminUser;

    private static String user = "user";
    private static String admin = "admin";

    private static boolean dataInitialized = false;
//
//    private void initData() {
//        if (!dataInitialized) {
//            t1 = (TeacherService) teacherService.create("name1", "surname1", LocalDate.of(2020, Month.JANUARY, 8));
//            teacherService.create("name2", "surname2", LocalDate.of(2021, Month.JANUARY, 8));
//            courseService.save("course1", "desc1", t1, CourseType.LETEN);
//
//
//            regularUser = userService.register(user, user, user, user, user, Role.ROLE_USER);
//            adminUser = userService.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
//            dataInitialized = true;
//        }
//    }

    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }


    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }


    private void initData() {
        if (!dataInitialized) {
            regularUser = userService.register(user, user, user, user, user, Role.ROLE_USER);
            adminUser = userService.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception{
        LoginPage loginPage = LoginPage.openLogin(this.driver);
        CoursesPage coursesPage = CoursesPage.to(this.driver);

        //ocekuvame da imame 0 edit 0 delete 0 add course
        coursesPage = LoginPage.doLogin(this.driver, loginPage, regularUser.getUsername(), regularUser.getPassword());
        coursesPage.assertElements(0, 0, 0);
        coursesPage = LoginPage.doLogin(this.driver, loginPage, adminUser.getUsername(), adminUser.getPassword());
        coursesPage.assertElements(1, 1, 1);
    }

//dopolnitelno baranje, uste nekoj generaciski test, 3 integraciski testovi


}
