package pl.jakub.myWebsite.pages.header;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderMethods {
    @FindBy(id = HeaderData.HEADER_GAME_ID)
    private WebElement headerGameButton;
    @FindBy (xpath = HeaderData.BALLON_HEADER_XPATH)
    private WebElement ballonHeaderButton;
    @FindBy (xpath = HeaderData.MATCH_HEADER_XPATH)
    private WebElement mathHeader;
    public void clickGames(){
        headerGameButton.click();
    }
    public void clickBallon(){
        ballonHeaderButton.click();
    }
    public void clickMath(){
        mathHeader.click();
    }
}
