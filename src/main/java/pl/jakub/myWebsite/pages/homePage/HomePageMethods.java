package pl.jakub.myWebsite.pages.homePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageMethods {
    @FindBy (id = HomePageData.ASSERTION_ID)
    private WebElement assertion;

    public String getAssertion(){
        return assertion.getAttribute("innerHTML");
    }
}
