package uipattern.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ScooterOrderMainPage extends MainPageWait {

    //локаторы кнопки Заказать
    //локатор верхней кнопки Заказать
    private By orderButtonUp = By.cssSelector(".Button_Button__ra12g");
    //локатор нижней кнопки Заказать
    private By orderButtonDown = By.cssSelector(".Home_FinishButton__1_cWm");

    //локаторы формы "Для кого самокат"
    //локаторы полей ввода: Имя, Фамилия, Адрес, Телефон
    private By inputs = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN']");
    //локатор поля Станция метро
    private By metroStation = By.xpath(".//input[@class='select-search__input']");
    //локатор кнопки Далее
    private By then = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //локаторы формы "Про аренду"
    //локатор поля Когда привезти самокат
    private By date = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN']");
    //локатор поля Срок аренды
    private By day = By.xpath(".//div[@class='Dropdown-placeholder']");
    //локатор поля Цвет самоката
    private By color = By.cssSelector(".Checkbox_Label__3wxSf");
    //локатор кнопки Заказать
    private By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //локатор кнопки Да
    private By orderButtonYes = By.xpath("//*[text()='Да']");
    //локатор появления оформленного заказа
    private By orderText = By.cssSelector(".Order_ModalHeader__3FDaJ");

    //конструктор класса
    public ScooterOrderMainPage(WebDriver driver){
        this.driver = driver;
    }

    //Методы
    // метод открытия страницы
    public void openMainPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    // метод клика по верхней кнопке Заказать
    public void clickOrderButtonUp() {
        driver.findElement(orderButtonUp).click();
    }

    // метод клика по нижней кнопке Заказать
    public void clickOrderButtonDown() {
        driver.findElement(orderButtonDown).click();
    }

    // метод клика по кнопке Да
    public void clickOrderButtonUpYes() {
        driver.findElement(orderButtonYes).click();
    }

    //Заполнение полей ввода формы "Для кого самокат"
    //метод заполняет поле ввода Имя, Фамилия, Адрес, Телефон
    public void setInputsValues(String name, String surname, String address,String phone) {
        List<WebElement> listInputs = driver.findElements(inputs);
        listInputs.get(0).sendKeys(name);
        listInputs.get(1).sendKeys(surname);
        listInputs.get(2).sendKeys(address);
        listInputs.get(3).sendKeys(phone);
    }

    //метод выбирает станцию метро
    public void setMetroStation() {
        driver.findElement(By.xpath(".//input[@class='select-search__input']")).click();
        driver.findElement(metroStation).sendKeys("Аэропорт");
        driver.findElement(By.cssSelector(".select-search__select")).click();
    }

    //метод кликает по кнопке Далее
    public void clickThenButton() {
        driver.findElement(then).click();
    }


    //Заполнение полей ввода формы "Про аренду"
    //метод заполняет поле выбора Даты
    public void setDate() {
        driver.findElement(date).click();
        driver.findElement(By.xpath(".//div[@class='react-datepicker__day react-datepicker__day--026']")).click();
    }

    //метод заполняет поле выбора Срока аренды
    public void setDay() {
        driver.findElement(day).click();
        driver.findElement(By.cssSelector(".Dropdown-option")).click();
    }

    //метод заполняет поле выбора Цвета самоката
    public void setColor() {
        driver.findElement(color).click();
    }

    //метод кликает по кнопке Заказать
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }


    //Объединенный метод заказа самоката в приложении по верхней кнопке Заказать
    public void scooterOrderUp(String name, String surname, String address, String phone) {
        waitForLoadCSS(orderButtonUp);
        clickOrderButtonUp();
        setUserData(name, surname, address, phone);
        setScooterDelivery();
        clickOrderButtonUpYes();
        waitForLoadCSS(orderText);

    }

    // метод ввода данных о доставке и самокате
    private void setScooterDelivery() {
        setDate();
        setDay();
        setColor();
        clickOrderButton();
    }

    // метод ввода данных о пользователе
    private void setUserData(String name, String surname, String address, String phone) {
        setInputsValues(name, surname, address, phone);
        setMetroStation();
        clickThenButton();
    }


    //Объединенный метод заказа самоката в приложении по нижней кнопке Заказать
    public void scooterOrderDown(String name, String surname, String address, String phone) {
    //  driver.findElement(By.id("rcc-confirm-button")).click(); // согласие на куки
        waitForLoadCSS(orderButtonDown);
        WebElement element = driver.findElement(orderButtonDown);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        clickOrderButtonDown();
        setUserData(name, surname, address, phone);
        setScooterDelivery();
        clickOrderButtonUpYes();
        waitForLoadCSS(orderText);
    }

    // метод получения текста элемента
    public String getActualText() {
        return driver.findElement(orderText).getText();
    }
}
