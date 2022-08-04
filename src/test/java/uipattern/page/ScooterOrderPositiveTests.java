package uipattern.page;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.*;

public class ScooterOrderPositiveTests {
    private WebDriver driver;


    @Before
    public void setup() {
          WebDriverManager.chromedriver().setup();
          System.setProperty("webdriver.chrome.driver","C:\\WebDriver\\bin\\chromedriver_win32\\chromedriver.exe");
          driver = new ChromeDriver();

    //   System.setProperty("webdriver.gecko.driver","C:\\WebDriver\\bin\\geckodriver\\geckodriver.exe");
    //   driver = new FirefoxDriver();
    }


    //Тест позитивного сценария заказа самоката на верхнюю кнопку Заказать
    @Test
    public void scooterOrderUp() {
        ScooterOrderMainPage objScooterOrderUp = new ScooterOrderMainPage(driver);
        objScooterOrderUp.openMainPage();
        objScooterOrderUp.scooterOrderUp("Иван", "Иванов","Центр","89999999999");
        String actual = driver.findElement(By.cssSelector(".Order_ModalHeader__3FDaJ")).getText();
        assertTrue(actual.contains("Заказ оформлен"));
    }

    //Тест позитивного сценария заказа самоката на нижнюю кнопку Заказать
    @Test
    public void scooterOrderDown() {
        ScooterOrderMainPage objScooterOrderDown = new ScooterOrderMainPage(driver);
        objScooterOrderDown.openMainPage();
        objScooterOrderDown.scooterOrderDown("Петр", "Петров","Окраина","89991110000");
        String actual = driver.findElement(By.cssSelector(".Order_ModalHeader__3FDaJ")).getText();
        assertTrue(actual.contains("Заказ оформлен"));
    }


    @After
    public void teardown() {
        driver.quit(); // Закрой браузер
    }

}
