package pl.jakub.myWebsite.pages;

import org.testng.annotations.Test;
import pl.jakub.myWebsite.cfg.WebDriverConfig;

public class CloseDriver {
    @Test
    public void close(){
        WebDriverConfig.closeDriver();
    }
}
