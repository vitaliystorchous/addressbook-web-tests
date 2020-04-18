package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.pft.addressbook.model.LoginData;

import java.time.Duration;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(LoginData loginData) {
        click(By.cssSelector(".navbar__link--login"));
        type(By.id("email"), loginData.getLogin());
        type(By.id("password"), loginData.getPassword());
        click(By.xpath("//form[@class=\"js-login-form\"]//input[@value=\"Log in\"]"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class=\"font-ui sites dashboard\"]")));
    }

}
