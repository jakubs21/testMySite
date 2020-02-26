package pl.jakub.myWebsite.pages.homePage;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakub.myWebsite.cfg.WebDriverConfig;
import pl.jakub.myWebsite.pages.header.HeaderMethods;


public class HomePageTest {
    private boolean init = true;
    private HomePageMethods homePageMethods;
    private HeaderMethods headerMethods;
    @BeforeMethod
    public void init(){
        if(init){
            WebDriverConfig.Initialize();
            homePageMethods = PageFactory.initElements(WebDriverConfig.getWebDriverInstance(), HomePageMethods.class);
            headerMethods = PageFactory.initElements(WebDriverConfig.getWebDriverInstance(), HeaderMethods.class);
        }
    }
    @Test
    public void openHomePage(){
        WebDriverConfig.getWebDriverInstance().get(HomePageData.WEBSITE_URL);
        headerMethods.clickGames();
        headerMethods.clickBallon();
        Assert.assertEquals(homePageMethods.getAssertion().trim(),"<p>Balloon Shooter</p>");
    }
}
