package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String login, String password) {
        click(By.cssSelector(".navbar__link--login"));
        type(By.id("email"), login);
        type(By.id("password"), password);
        click(By.xpath("//form[@class=\"js-login-form\"]//input[@value=\"Log in\"]"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class=\"font-ui sites dashboard\"]")));
    }

}
