package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.pft.addressbook.model.LoginData;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private SessionHelper sessionHelper;
    private WebDriver wd;
    private NavigationHelper navigationHelper;
    private MenuEditorHelper menuEditorHelper;
    String login = "rufjtigk+80@gmail.com";
    String password = "qweriuyt";
    public String customPageName = "Test custom page";
    Dimension dimension = new Dimension(1920, 1024);
    private PageEditorHelper pageEditorHelper;

    public void init() {
        wd = new ChromeDriver();
        wd.manage().window().setSize(dimension);
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("https://www.format.com/");
        menuEditorHelper = new MenuEditorHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        pageEditorHelper = new PageEditorHelper(wd);
        sessionHelper.login(new LoginData(login, password));
    }

    public void goBackToPagesFromPageEditor() {
        wd.findElement(By.cssSelector(".whitespace-no-wrap")).click();
    }

    public void createCustomPage(String pageName) {
        wd.findElement(By.xpath("//button[@class=\"btn\"]")).click();
        wd.findElement(By.cssSelector(".site-menu-action-header li:nth-child(3)")).click();
        wd.findElement(By.cssSelector(".page-type-modal-input")).sendKeys(pageName);
        wd.findElement(By.cssSelector(".btn-primarycolor")).click();

    }

    public void stop() {
        wd.quit();
    }

    public MenuEditorHelper getMenuEditorHelper() { return menuEditorHelper; }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public PageEditorHelper getPageEditorHelper() { return pageEditorHelper; }
}
