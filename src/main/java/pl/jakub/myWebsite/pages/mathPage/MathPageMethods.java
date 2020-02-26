package pl.jakub.myWebsite.pages.mathPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.jakub.myWebsite.cfg.WebDriverConfig;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class MathPageMethods {
    @FindBy(id = MathPageData.QUESTION_ID)
    private WebElement question;
    @FindBy(id = MathPageData.START_MATH_GAME_ID)
    private WebElement startMath;
    @FindAll(@FindBy (className = MathPageData.BOX_CLASS))
    private List<WebElement> answerBoxes;
    @FindBy(id = MathPageData.BOX_1_ID)
    private WebElement box1;
    @FindBy(id = MathPageData.BOX_2_ID)
    private WebElement box2;
    @FindBy(id = MathPageData.BOX_3_ID)
    private WebElement box3;
    @FindBy(id = MathPageData.BOX_4_ID)
    private WebElement box4;
    @FindBy(id = MathPageData.TIME_REMAINING )
    private WebElement timeRemaing;
    @FindBy(id = MathPageData.CORRECT_ID)
    private WebElement correctMessage;
    @FindBy(id = MathPageData.GAME_OVER_ID)
    private WebElement gameOver;
    @FindBy(id = MathPageData.SCORE_ID)
    private WebElement scoreValue;
    @FindBy(id = MathPageData.WRONG_ID)
    private WebElement wrongMessage;
    WebDriverWait wait = new WebDriverWait(WebDriverConfig.getWebDriverInstance(), 10);
//    private int countedScore = 0;
    private int k, m;
    private int correctAnswer;
    private boolean xFound=false;
//    private int endScore;
//    public int getEndScore(){
//        endScore=Integer.parseInt(scoreValue.getAttribute("innerHTML").trim());
//        return endScore;
//    }
    public int getCountedScore(){
        return Integer.parseInt(scoreValue.getAttribute("innerHTML").trim());
    }
    public int getCorrectAnswer(){
        int first, second, correctAnswer=0;
        k=0;
        m=0;
        String questionString=question.getAttribute("innerHTML").trim();
        char charsFromQuestion[]=  questionString.toCharArray();
        char charsForFirstNumber[]= new char[3];
        char charsForSecondNumber[]= new char[3];
        for (char searchX:charsFromQuestion
             ) {
            if(searchX!='x' && xFound==false){
                charsForFirstNumber[k]=searchX;
                k++;
            }else {
                xFound=true;
            }
            if(searchX!='x' && xFound==true){
                charsForSecondNumber[m]=searchX;
                m++;
            }
        }
        String firstString= new String(charsForFirstNumber);
        String secondString= new String(charsForSecondNumber);
        first = Integer.parseInt(firstString.trim());
        second = Integer.parseInt(secondString.trim());
        correctAnswer=first*second;
        xFound=false;
        return correctAnswer;

    }
    public void startMathGame(){
        startMath.click();
    }
    public void clickIfCorrect(WebElement box){
        correctAnswer=getCorrectAnswer();
        if (correctAnswer == Integer.parseInt(box.getText())) {
            wait.until(ExpectedConditions.elementToBeClickable(box));
            box.click();
            //countedScore=countedScore+1;
            wait.until(ExpectedConditions.invisibilityOf(correctMessage));
        }
    }
    public void clickIfWrong(WebElement box){
        correctAnswer=getCorrectAnswer();
        if (correctAnswer != Integer.parseInt(box.getText())) {
            wait.until(ExpectedConditions.elementToBeClickable(box));
            box.click();
        }
    }
//    public void sleepBetweenClicks(int sleepTime){
//        try
//        {
//            Thread.sleep(sleepTime);
//        }
//        catch(InterruptedException ex)
//        {
//            Thread.currentThread().interrupt();
//        }
//    }
    public void clickWrongBox(){
        if(!wrongMessage.isDisplayed())clickIfWrong(box1);
        if(!wrongMessage.isDisplayed())clickIfWrong(box2);
        if(!wrongMessage.isDisplayed())clickIfWrong(box3);
        if(!wrongMessage.isDisplayed())clickIfWrong(box4);
    }
    public boolean isWrongMessageVisible(){
        return wrongMessage.isDisplayed();
    }
    public void clickCorrectBox(){
        do {
            clickIfCorrect(box1);
            //sleepBetweenClicks(1000);
            if(Integer.parseInt(scoreValue.getAttribute("innerHTML").trim())==15) {break;}
            clickIfCorrect(box2);
            //sleepBetweenClicks(1000);
            if(Integer.parseInt(scoreValue.getAttribute("innerHTML").trim())==15){ break;}
            clickIfCorrect(box3);
            //sleepBetweenClicks(1000);
            if(Integer.parseInt(scoreValue.getAttribute("innerHTML").trim())==15) {break;}
            clickIfCorrect(box4);
            //sleepBetweenClicks(1000);
        }while(Integer.parseInt(scoreValue.getAttribute("innerHTML").trim())<15); //timeRemaing.isDisplayed()
    }
    public boolean isGameOverVisible(){
        {
            if(gameOver.isDisplayed())return true;
        //wait.until(ExpectedConditions.visibilityOf(gameOver));
        }while (timeRemaing.isDisplayed());
        return gameOver.isDisplayed();
    }
//    public boolean isScoreEqualScoreCount(){
//        if( countedScore== Integer.parseInt(scoreValue.getAttribute("innerHTML").trim())){
//            return true;
//        }
//        return false;
//    }
    public void clearScore(){
        //countedScore=0;
    }
    public String matchGameOpen(){
        return startMath.getAttribute("innerHTML").trim();
    }
}
