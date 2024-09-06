import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class Authorization extends Data{  //Проверки страницы авторизации

    private By login=By.xpath("//*[@id=\"loginEmail\"]");  // Поле Email
    private By password=By.xpath("//*[@id=\"loginPassword\"]"); // Поле Пароль
    private By btnEnter=By.id("authButton"); // Кнопка Вход
    private By name=By.id("dataName"); // поле Имя на страницы анкеты
    private By warningEmailOrPass = By.id("invalidEmailPassword"); // Сообщение о неверном логине или пароле
    private By warningEmail = By.id("emailFormatError"); // Сообщение о неверном формате емэйла

    WebDriver driver = new ChromeDriver();
    Data data=new Data();

    @Test  //Проверка, что при вводе корректного емэйла и пароля пользователь авторизуется и перенаправляется на нужную страницу
    public void authorization() throws InterruptedException {

        driver.get(data.getUrl);
        driver.manage().window().maximize();
        driver.findElement(login).sendKeys(data.email);
        driver.findElement(password).sendKeys(data.password);
        driver.findElement(btnEnter).click();
        driver.findElement(name);
        sleep(3000);
        driver.quit();
    }

    @Test   /* Проверка, что нельзя авторизоваться при пустом поле "Пароль" и выводится валидационное сообщение. Текст некоторых валидационных
    сообщений здесь и далее я считаю некорректным, но тк нет требований, для теста я приняла все валидационные сообщения за корректные*/
    public void emptyPass() throws InterruptedException {

        driver.get(data.getUrl);
        driver.findElement(login).sendKeys(data.email);
        driver.findElement(btnEnter).click();
        WebElement text = driver.findElement(warningEmailOrPass);
        assert text.getText().equals(data.warningEmailOrPass);
        sleep(3000);
        driver.quit();
    }

    @Test   /* Проверка, что нельзя авторизоваться при пустом поле Емэйл и выводится валидационное сообщение */
    public void emptyEmail() throws InterruptedException {

        driver.get(data.getUrl);
        driver.findElement(password).sendKeys(data.password);
        driver.findElement(btnEnter).click();
        WebElement text = driver.findElement(warningEmail);
        assert text.getText().equals(data.warningEmail);
        sleep(3000);
        driver.quit();
    }

    @Test  /* Проверка, что нельзя авторизоваться при вводе неверного емэйла и выводится валидационное сообщение */
    public void wrongEmail() throws InterruptedException {

        driver.get(data.getUrl);
        driver.findElement(login).sendKeys(data.wrongEmail);
        driver.findElement(password).sendKeys(data.password);
        driver.findElement(btnEnter).click();
        assertEquals(driver.findElement(warningEmailOrPass).getText(),data.warningEmailOrPass);
        sleep(3000);
        driver.quit();
    }

    @Test  /* Проверка, что нельзя авторизоваться при вводе неверного пароля и выводится валидационное сообщение */
    public void wrongPass() throws InterruptedException {

        driver.get(data.getUrl);
        driver.findElement(login).sendKeys(data.email);
        driver.findElement(password).sendKeys(data.wrongPass);
        driver.findElement(btnEnter).click();
        assertEquals(driver.findElement(warningEmailOrPass).getText(),data.warningEmailOrPass);
        sleep(3000);
        driver.quit();
    }
/* Можно по аналогии написать еще много негативных тестов, таких как пробелы вместо/до/после емэйла и/или пароля,
   оставить оба поля пустыми, ввести слишком длинные значения в поля итд.
 */
}

