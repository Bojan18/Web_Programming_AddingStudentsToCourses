package mk.ukim.finki.wp.lab.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class CoursesPage extends AbstractPage{
    public CoursesPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(className = "deleteBtns")
    private List<WebElement> deleteButtons;

    @FindBy(className = "editBtns")
    private List<WebElement> editButtons;

    @FindBy(className = "addBtns")
    private List<WebElement> addButtons;

    public static CoursesPage to(WebDriver driver) {
        get(driver, "/courses");
        return PageFactory.initElements(driver, CoursesPage.class);
    }

    public void assertElements(int deleteButtons, int editButtons, int addButtons){
        Assert.assertEquals("edit is visible", editButtons, this.getEditButtons().size());
        Assert.assertEquals("delete is visible", deleteButtons, this.getDeleteButtons().size());
        Assert.assertEquals("add is visible", addButtons, this.getAddButtons().size());
    }


}
