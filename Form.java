import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class Form extends Data{ //Проверки анкеты

    private By email = By.id("dataEmail"); // Поле E-mail на странице анкеты
    private By name=By.id("dataName"); // Поле Имя на странице анкеты
    private By gender=By.id("dataGender"); // Выпадающий список Пол на странице анкеты
    private By checkBoxOne=By.xpath("//*[@id=\"dataCheck11\"]"); // Первый чек-бокс
    private By checkBoxTwo=By.xpath("//*[@id=\"dataCheck12\"]"); // Второй чек-бокс
    private By radioButtonOne=By.xpath("//*[@id=\"dataSelect21\"]"); // Первый радиобаттон
    private By radioButtonTwo=By.xpath("//*[@id=\"dataSelect22\"]"); // Второй радиобаттон
    private By radioButtonThree=By.xpath("//*[@id=\"dataSelect23\"]"); // Третий радиобаттон
    private By button=By.id("dataSend"); // Кнопка "Добавить"
    private By modal=By.cssSelector("body > div.uk-modal.uk-open > div > div"); // Модальное окно, подтверждающее успешное добавление данных
    private By modalButton=By.cssSelector("body > div.uk-modal.uk-open > div > div > div.uk-modal-footer.uk-text-right > button"); // Кнопка "Ок" в модальном окне
    private By resultEmail=By.xpath("/html/body/div[2]/form/table/tbody/tr[1]/td[1]"); // Поле емэйл в таблице
    private By resultName=By.cssSelector("#dataTable > tbody > tr:nth-child(1) > td:nth-child(2)"); // Поле Имя в таблице
    private By resultGender=By.cssSelector("#dataTable > tbody > tr:nth-child(1) > td:nth-child(3)"); // Поле Пол в таблице
    private By resultCheckBox=By.cssSelector("#dataTable > tbody > tr:nth-child(1) > td:nth-child(4)"); // Поле Выбор 1 в таблице
    private By resultRadio=By.cssSelector("#dataTable > tbody > tr:nth-child(1) > td:nth-child(5)"); // Поле Выбор 2 в таблице
    private By warningEmail = By.id("emailFormatError"); // Ошибка о некорректном формате емэйла
    private By warningEmptyName=By.id("dataAlertsHolder"); // Ошибка о пустом поле Имя

    WebDriver driver=new ChromeDriver();
    Data data=new Data();

    @Test /* Проверка, что при вводе корректного емэйла и имени, выборе первого чек-бокса и первого радиобаттона и
    оставленном по умолчанию поле "Пол", можно добавить данные в анкету. */
    public void fillFormOne() throws InterruptedException {

        driver.get(data.getUrl);
        driver.findElement(By.xpath("//*[@id=\"loginEmail\"]")).sendKeys(data.email);
        driver.findElement(By.xpath("//*[@id=\"loginPassword\"]")).sendKeys(data.password);
        driver.findElement(By.id("authButton")).click();

        driver.findElement(email).sendKeys(data.email);
        driver.findElement(name).sendKeys(data.nameMEn);
        driver.findElement(checkBoxOne).click();
        driver.findElement(radioButtonOne).click();
        driver.findElement(button).click();
        assertEquals(data.modalForm,driver.findElement(modal).getText());
        driver.findElement(modalButton).click();
        assertEquals(data.email,driver.findElement(resultEmail).getText());
        assertEquals(data.nameMEn,driver.findElement(resultName).getText());
        assertEquals(data.genderMale,driver.findElement(resultGender).getText());
        assertEquals(data.checkBoxOne,driver.findElement(resultCheckBox).getText());
        assertEquals(data.radioOne,driver.findElement(resultRadio).getText());
        sleep(3000);
        driver.quit();
    }

    @Test /* Проверка, что при вводе корректного емэйла и имени, выборе и отмене первого чек-бокса, выборе второго
     чек-бокса и второго радиобаттона и выборе женского пола, можно добавить данные в анкету. */
    public void fillFormTwo() throws InterruptedException {

        driver.get(data.getUrl);
        driver.findElement(By.xpath("//*[@id=\"loginEmail\"]")).sendKeys(data.email);
        driver.findElement(By.xpath("//*[@id=\"loginPassword\"]")).sendKeys(data.password);
        driver.findElement(By.id("authButton")).click();

        driver.findElement(email).sendKeys(data.email);
        driver.findElement(name).sendKeys(data.nameFRus);
        Select select=new Select(driver.findElement(gender));
        select.selectByVisibleText(data.genderFemale);
        driver.findElement(checkBoxOne).click();
        driver.findElement(checkBoxTwo).click();
        driver.findElement(checkBoxOne).click();
        driver.findElement(radioButtonTwo).click();
        driver.findElement(button).click();
        assertEquals(data.modalForm,driver.findElement(modal).getText());
        driver.findElement(modalButton).click();
        assertEquals(data.email,driver.findElement(resultEmail).getText());
        assertEquals(data.nameFRus,driver.findElement(resultName).getText());
        assertEquals(data.genderFemale,driver.findElement(resultGender).getText());
        assertEquals(data.checkBoxTwo,driver.findElement(resultCheckBox).getText());
        assertEquals(data.radioTwo,driver.findElement(resultRadio).getText());
        sleep(3000);
        driver.quit();
    }

    @Test /* Проверка, что при вводе корректного емэйла и имени, выборе обоих чек-боксов и сначала первого,
     потом третьего радиобаттона и женского пола, можно добавить данные в анкету. */
    public void fillFormThree() throws InterruptedException {

        driver.get(data.getUrl);
        driver.findElement(By.xpath("//*[@id=\"loginEmail\"]")).sendKeys(data.email);
        driver.findElement(By.xpath("//*[@id=\"loginPassword\"]")).sendKeys(data.password);
        driver.findElement(By.id("authButton")).click();

        driver.findElement(email).sendKeys(data.email);
        driver.findElement(name).sendKeys(data.nameFRus);
        Select select=new Select(driver.findElement(gender));
        select.selectByVisibleText(data.genderFemale);
        driver.findElement(checkBoxOne).click();
        driver.findElement(checkBoxTwo).click();
        driver.findElement(radioButtonOne).click();
        driver.findElement(radioButtonThree).click();
        driver.findElement(button).click();
        assertEquals(data.modalForm,driver.findElement(modal).getText());
        driver.findElement(modalButton).click();
        assertEquals(data.email,driver.findElement(resultEmail).getText());
        assertEquals(data.nameFRus,driver.findElement(resultName).getText());
        assertEquals(data.genderFemale,driver.findElement(resultGender).getText());
        assertEquals(data.checkBoxBoth,driver.findElement(resultCheckBox).getText());
        assertEquals(data.radioThree,driver.findElement(resultRadio).getText());
        sleep(3000);
        driver.quit();
    }

    @Test /* Проверка, что при вводе корректного емэйла и имени,при оставленном по умолчанию поле "Пол"
     и не выбранном чек-боксе и радиобаттоне, можно добавить данные в анкету. Здесь мне непонятно, верно ли в
     таблице заполнен пустой чек-бокс и не заполнен радиобаттон, нужны требования */
    public void fillFormFour() throws InterruptedException {

        driver.get(data.getUrl);
        driver.findElement(By.xpath("//*[@id=\"loginEmail\"]")).sendKeys(data.email);
        driver.findElement(By.xpath("//*[@id=\"loginPassword\"]")).sendKeys(data.password);
        driver.findElement(By.id("authButton")).click();

        driver.findElement(email).sendKeys(data.email);
        driver.findElement(name).sendKeys(data.nameMRus);
        driver.findElement(button).click();
        assertEquals(data.modalForm,driver.findElement(modal).getText());
        driver.findElement(modalButton).click();
        assertEquals(data.email,driver.findElement(resultEmail).getText());
        assertEquals(data.nameMRus,driver.findElement(resultName).getText());
        assertEquals(data.genderMale,driver.findElement(resultGender).getText());
        assertEquals(data.checkBoxEmpty,driver.findElement(resultCheckBox).getText());
        assertEquals(data.radioEmpty,driver.findElement(resultRadio).getText());
        sleep(3000);
        driver.quit();
    }

    @Test /* Проверка, что при вводе в поле емэйл спецсимволов, можно добавить данные в анкету. Тест упал, это баг. Аналогично
    можно проверить  буквы в верхнем регистре, цифры итд. */
    public void fillFormNoEmailSymbols() throws InterruptedException {

        driver.get(data.getUrl);;
        driver.findElement(By.xpath("//*[@id=\"loginEmail\"]")).sendKeys(data.email);
        driver.findElement(By.xpath("//*[@id=\"loginPassword\"]")).sendKeys(data.password);
        driver.findElement(By.id("authButton")).click();

        driver.findElement(email).sendKeys(data.emailSymbols);
        driver.findElement(name).sendKeys(data.nameMRus);
        driver.findElement(button).click();
        assertEquals(data.modalForm,driver.findElement(modal).getText());
        driver.findElement(modalButton).click();
        assertEquals(data.emailSymbols,driver.findElement(resultEmail).getText());
        assertEquals(data.nameMRus,driver.findElement(resultName).getText());
        assertEquals(data.genderMale,driver.findElement(resultGender).getText());
        assertEquals(data.checkBoxEmpty,driver.findElement(resultCheckBox).getText());
        assertEquals(data.radioEmpty,driver.findElement(resultRadio).getText());
        sleep(3000);
        driver.quit();
    }

    @Test /* Проверка, что при оставленном поле емэйл пустым, данные не добавятся в анкету и появится валидационное сообщение*/
    public void fillFormNoEmail() throws InterruptedException {

        driver.get(data.getUrl);;
        driver.findElement(By.xpath("//*[@id=\"loginEmail\"]")).sendKeys(data.email);
        driver.findElement(By.xpath("//*[@id=\"loginPassword\"]")).sendKeys(data.password);
        driver.findElement(By.id("authButton")).click();

        driver.findElement(name).sendKeys(data.nameMRus);
        driver.findElement(button).click();
        assertEquals(data.warningEmail,driver.findElement(warningEmail).getText());
        sleep(3000);
        driver.quit();
    }

    @Test /* Проверка, что при оставленном поле "Имя" пустым, данные не добавятся в анкету и появится валидационное сообщение*/
    public void fillFormNoName() throws InterruptedException {

        driver.get(data.getUrl);;
        driver.findElement(By.xpath("//*[@id=\"loginEmail\"]")).sendKeys(data.email);
        driver.findElement(By.xpath("//*[@id=\"loginPassword\"]")).sendKeys(data.password);
        driver.findElement(By.id("authButton")).click();

        driver.findElement(email).sendKeys(data.email);
        driver.findElement(button).click();
        assertEquals(data.warningEmptyName,driver.findElement(warningEmptyName).getText());
        sleep(3000);
        driver.quit();
    }

    @Test /* Проверка, что при вводе некорректного формата емэйла и корректного имени, нельзя добавить данные в анкету и появится
    валидационное сообщение. Аналогичные проверки при отсутствии точки после доменного имени итд */
    public void fillFormEmailWrongFormat() throws InterruptedException {

        driver.get(data.getUrl);
        driver.findElement(By.xpath("//*[@id=\"loginEmail\"]")).sendKeys(data.email);
        driver.findElement(By.xpath("//*[@id=\"loginPassword\"]")).sendKeys(data.password);
        driver.findElement(By.id("authButton")).click();

        driver.findElement(email).sendKeys(data.emailWrongFormat);
        driver.findElement(name).sendKeys(data.nameMRus);
        driver.findElement(button).click();
        assertEquals(data.warningEmail,driver.findElement(warningEmail).getText());
        sleep(3000);
        driver.quit();
    }

    @Test /* Проверка, что при вводе слишком короткого емэйла и корректного имени, нельзя добавить данные в анкету и появится
    валидационное сообщение. Тест упал, это баг, доменное имя и зона не могут содержать 1 символ*/
    public void fillFormEmailShort() throws InterruptedException {

        driver.get(data.getUrl);
        driver.findElement(By.xpath("//*[@id=\"loginEmail\"]")).sendKeys(data.email);
        driver.findElement(By.xpath("//*[@id=\"loginPassword\"]")).sendKeys(data.password);
        driver.findElement(By.id("authButton")).click();

        driver.findElement(email).sendKeys(data.emailShort);
        driver.findElement(name).sendKeys(data.nameMRus);
        driver.findElement(button).click();
        assertEquals(data.warningEmail,driver.findElement(warningEmail).getText());
        sleep(3000);
        driver.quit();
    }
}
