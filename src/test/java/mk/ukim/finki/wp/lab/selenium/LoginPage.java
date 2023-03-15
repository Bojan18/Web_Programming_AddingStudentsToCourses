package mk.ukim.finki.wp.lab.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage{

    private WebElement username;
    private WebElement password;
    private WebElement submit;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

//    public static LoginPage openLogin(WebDriver driver){
//        //napravi povik kroz login localhost:9999/login
//        get(driver, "/login");
//        //da znaeme na koja lokacija sme
//        System.out.println(driver.getCurrentUrl());
//        //dobija ja login stranata
////        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
//        return PageFactory.initElements(driver, LoginPage.class);
//    }

    public static LoginPage openLogin(WebDriver driver) {
        get(driver, "/login");
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, LoginPage.class);

    }


    public static CoursesPage doLogin(WebDriver driver, LoginPage loginPage, String username, String password){
        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);
        loginPage.submit.click();
        System.out.println(driver.getCurrentUrl());

//        CoursesPage coursesPage = PageFactory.initElements(driver, CoursesPage.class);
        return PageFactory.initElements(driver, CoursesPage.class);
    }

    public static LoginPage logout(WebDriver driver) {
        get(driver, "/logout");
        return PageFactory.initElements(driver, LoginPage.class);
    }


}
