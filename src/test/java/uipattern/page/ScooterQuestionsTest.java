package uipattern.page;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)

public class ScooterQuestionsTest {

   public ScooterQuestionsTest(String numberOfAccording, String expected) {
       this.numberOfAccording = numberOfAccording;
       this.expected = expected;
   }


    @Parameterized.Parameters

    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {"0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"7", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},

        });
    }
    private WebDriver driver;
    String numberOfAccording;
    String expected;

    @Before
    public void setup() {
          WebDriverManager.chromedriver().setup();
          System.setProperty("webdriver.chrome.driver","C:\\WebDriver\\bin\\chromedriver_win32\\chromedriver.exe");
          driver = new ChromeDriver();
      //  System.setProperty("webdriver.gecko.driver","C:\\WebDriver\\bin\\geckodriver\\geckodriver.exe");
      //  driver = new FirefoxDriver();
    }

    @Test
    public void scooterQuestions() {
        ScooterQuestions scooterQuestions = new ScooterQuestions(driver,numberOfAccording);
        scooterQuestions.openMainPage();
        String actual = scooterQuestions.getScooterAnswers();
        assertEquals(expected, actual);
    }

    @After
    public void teardown() {
        driver.quit(); // Закрой браузер
    }
}

