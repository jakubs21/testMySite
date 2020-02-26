package pl.jakub.myWebsite.pages.mathPage;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.jakub.myWebsite.cfg.WebDriverConfig;
import pl.jakub.myWebsite.pages.header.HeaderMethods;
import pl.jakub.myWebsite.pages.homePage.HomePageData;

public class MathPageTest {
    private boolean init = true;
    private MathPageMethods mathPageMethods;
    private HeaderMethods headerMethods;
    @BeforeMethod
    public void init(){
        if(init){
            WebDriverConfig.Initialize();
            mathPageMethods = PageFactory.initElements(WebDriverConfig.getWebDriverInstance(), MathPageMethods.class);
            headerMethods = PageFactory.initElements(WebDriverConfig.getWebDriverInstance(), HeaderMethods.class);
        }
    }
    @Test
    public void openMatchPage(){
        //WebDriverConfig.getWebDriverInstance().get(HomePageData.WEBSITE_URL);
        headerMethods.clickGames();
        headerMethods.clickMath();
        Assert.assertEquals(mathPageMethods.matchGameOpen(),"Start game");
    }
    @Test
    public void startMatchGame(){
        mathPageMethods.startMathGame();
        mathPageMethods.clickCorrectBox();
        mathPageMethods.clickWrongBox();
        Assert.assertEquals(mathPageMethods.isWrongMessageVisible(),true);
        Assert.assertEquals(mathPageMethods.isGameOverVisible(),true);
    }
    @Test
    public void checkScore(){
        Assert.assertEquals(mathPageMethods.getCountedScore(),15);
//        Assert.assertEquals(mathPageMethods.isScoreEqualScoreCount(),true);
    }
//    @AfterMethod
//    public void clear(){
//        mathPageMethods.clearScore();
//    }
}
