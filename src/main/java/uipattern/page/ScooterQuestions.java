package uipattern.page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ScooterQuestions {

    private WebDriver driver;

    //локаторы кнопки с вопросами
    private By questionButton = By.cssSelector(".accordion__button");

    //локаторы ответов
    private String templateAnswers = "accordion__panel-";
    private By answers;

    //локаторы вопросов
    private By questions;
    private String templateQuestions = "accordion__heading-";

    // метод открытия страницы
    public void openMainPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    // метод получения вопросов
    public void getQuestions() {
        driver.findElement(questions).click();
    }

    // метод получения ответов
    public String getAnswers() {
        return driver.findElement(answers).getText();
    }

    public ScooterQuestions(WebDriver driver, String numberOfAccordind) {
        this.driver = driver;
        this.answers = By.id(templateAnswers.concat(numberOfAccordind));
        this.questions = By.id(templateQuestions.concat(numberOfAccordind));
    }

    // метод ожидания появления элемента по css
    private void waitForLoadCSS(By cssSelector) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(cssSelector));
    }

    // метод ожидания появления элемента по id
    private void waitForLoadID(By id) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(id));
    }

    // объединенный метод получения ответов на вопросы
    public String getScooterAnswers() {
        waitForLoadCSS(questionButton);
        WebElement element = driver.findElement(questionButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        getQuestions();
        waitForLoadID(answers);
        return getAnswers();
    }
}