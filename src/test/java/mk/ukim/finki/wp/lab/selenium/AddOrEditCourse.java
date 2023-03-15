package mk.ukim.finki.wp.lab.selenium;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.enums.CourseType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddOrEditCourse extends AbstractPage{

    private WebElement name;
    private WebElement description;
    private WebElement courseType;

    private WebElement teacherId;

    private WebElement submit;

//    public static ProductsPage addProduct(WebDriver driver, String name, String price, String quantity, String category, String manufacturer) {
//        get(driver, "/products/add-form");
//        AddOrEditProduct addOrEditProduct = PageFactory.initElements(driver, AddOrEditProduct.class);
//        addOrEditProduct.name.sendKeys(name);
//        addOrEditProduct.price.sendKeys(price);
//        addOrEditProduct.quantity.sendKeys(quantity);
//        addOrEditProduct.category.click();
//        addOrEditProduct.category.findElement(By.xpath("//option[. = '" + category + "']")).click();
//        addOrEditProduct.manufacturer.click();
//        addOrEditProduct.manufacturer.findElement(By.xpath("//option[. = '" + manufacturer + "']")).click();
//
//        addOrEditProduct.submit.click();
//        return PageFactory.initElements(driver, ProductsPage.class);
//    }

    public static CoursesPage addCourse(WebDriver driver, String name, String description, CourseType courseType, Teacher teacher){
        AddOrEditCourse addOrEditCourse = PageFactory.initElements(driver, AddOrEditCourse.class);
        addOrEditCourse.name.sendKeys(name);
        addOrEditCourse.description.sendKeys(description);
        addOrEditCourse.courseType.click();
        addOrEditCourse.courseType.findElement(By.xpath("//option[. = '" + courseType + "']")).click();
        addOrEditCourse.teacherId.click();
        addOrEditCourse.teacherId.findElement(By.xpath("//option[. = '" + teacher + "']")).click();

        addOrEditCourse.submit.click();

        return PageFactory.initElements(driver, CoursesPage.class);
    }

//    public static ProductsPage editProduct(WebDriver driver, WebElement editButton, String name, String price, String quantity, String category, String manufacturer) {
//        editButton.click();
//        System.out.println(driver.getCurrentUrl());
//        AddOrEditProduct addOrEditProduct = PageFactory.initElements(driver, AddOrEditProduct.class);
//        addOrEditProduct.name.sendKeys(name);
//        addOrEditProduct.price.sendKeys(price);
//        addOrEditProduct.quantity.sendKeys(quantity);
//        addOrEditProduct.category.click();
//        addOrEditProduct.category.findElement(By.xpath("//option[. = '" + category + "']")).click();
//        addOrEditProduct.manufacturer.click();
//        addOrEditProduct.manufacturer.findElement(By.xpath("//option[. = '" + manufacturer + "']")).click();
//
//        addOrEditProduct.submit.click();
//        return PageFactory.initElements(driver, ProductsPage.class);
//    }

    public static CoursesPage editCourse(WebDriver driver, WebElement editButton, String name, String description, CourseType courseType, Teacher teacher){
        editButton.click();
        System.out.println(driver.getCurrentUrl());
        AddOrEditCourse addOrEditCourse = PageFactory.initElements(driver, AddOrEditCourse.class);
        addOrEditCourse.name.sendKeys(name);
        addOrEditCourse.description.sendKeys(description);
        addOrEditCourse.courseType.click();
        addOrEditCourse.courseType.findElement(By.xpath("//option[. = '" + courseType + "']")).click();
        addOrEditCourse.teacherId.click();
        addOrEditCourse.teacherId.findElement(By.xpath("//option[. = '" + teacher + "']")).click();

        addOrEditCourse.submit.click();

        return PageFactory.initElements(driver, CoursesPage.class);
    }

    public AddOrEditCourse(WebDriver webDriver) {
        super(webDriver);
    }
}
