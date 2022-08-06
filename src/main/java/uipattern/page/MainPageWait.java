package uipattern.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageWait {

    WebDriver driver;

    // метод ожидания появления элемента по css
    void waitForLoadCSS(By cssSelector) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(cssSelector));
    }

    // метод ожидания появления элемента по id
    void waitForLoadID(By id) {

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(id));
    }

}
