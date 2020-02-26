package pl.jakub.myWebsite.pages.ballonPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.jakub.myWebsite.cfg.WebDriverConfig;

import java.util.ArrayList;
import java.util.List;

public class BallonPageMethods {
    @FindBy (id = BallonPageData.START_GAME_BUTTON_ID)
    private WebElement startGameButton;
    @FindBy (id = BallonPageData.BALLOON_1_ID)
    private WebElement balloon1;
    @FindBy (id = BallonPageData.BALLOON_2_ID)
    private WebElement balloon2;
    @FindBy (id = BallonPageData.BALLOON_3_ID)
    private WebElement balloon3;
    @FindBy (id = BallonPageData.ASSERTION_SCORE_ID)
    private WebElement score;
    @FindBy (id = BallonPageData.ASSERTION_GAME_OVER_ID)
    private WebElement gameOverAssertion;
    @FindAll(@FindBy (className = BallonPageData.BALLOON_CLASS))
    private List<WebElement> balloons;
    @FindAll(@FindBy (xpath = BallonPageData.HEART_XPATH))
    private List<WebElement> hearts;

    private WebDriverWait wait = new WebDriverWait(WebDriverConfig.getWebDriverInstance(), 10);


    public void clickStartGame(){
        startGameButton.click();
    }
    public void clickBalloon(WebElement balloon){
        wait.until(ExpectedConditions.visibilityOf(balloon));
        balloon.click();
    }
    public void click3Ballons(){
        clickBalloon(balloon3);
        clickBalloon(balloon2);
        clickBalloon(balloon1);
        wait.until(ExpectedConditions.elementToBeClickable(balloon3));
        wait.until(ExpectedConditions.elementToBeClickable(balloon2));
        wait.until(ExpectedConditions.elementToBeClickable(balloon1));
    }
    public String getBalloon() {
        do{
        click3Ballons();
    }while (Integer.parseInt(score.getAttribute("innerHTML").trim())<10);
        wait.until(ExpectedConditions.elementToBeClickable(balloon3));
        wait.until(ExpectedConditions.elementToBeClickable(balloon2));
        wait.until(ExpectedConditions.elementToBeClickable(balloon1));
        return score.getAttribute("innerHTML").trim();
    }
    public boolean gameOver(){
        wait.until(ExpectedConditions.visibilityOf(gameOverAssertion));
        boolean gameOverVisible=false;
        if (gameOverAssertion.isDisplayed()){
            gameOverVisible=true;
        }
        return gameOverVisible;
    }
}
