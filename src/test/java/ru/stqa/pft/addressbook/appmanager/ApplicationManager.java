package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.pft.addressbook.model.LoginData;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private SessionHelper sessionHelper;
    private WebDriver wd;
    private NavigationHelper navigationHelper;
    private MenuEditorHelper menuEditorHelper;
    String login = "rufjtigk+81@gmail.com";
    String password = "qweriuyt";
    public String customPageName = "Test custom page (*Selenium*)";
    Dimension dimension = new Dimension(1920, 1024);
    private PageEditorHelper pageEditorHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }

        wd.manage().window().setSize(dimension);
        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wd.get("https://www.format.com/");
        menuEditorHelper = new MenuEditorHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        pageEditorHelper = new PageEditorHelper(wd);
        sessionHelper.login(new LoginData(login, password));
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
