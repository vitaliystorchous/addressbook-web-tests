package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.pft.addressbook.model.LoginData;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private SessionHelper sessionHelper;
    private WebDriver wd;
    private NavigationHelper navigationHelper;
    private MenuEditorHelper menuEditorHelper;
    private StoreHelper storeHelper;
    private BlogEditorHelper blogEditorHelper;
    String login = "rufjtigk+84@gmail.com";
    String password = "qweriuyt";
    public String customPageName = "Test custom page (*Selenium*)";
    public String galleryName = "Test gallery (*Selenium*)";
    public String collectionName = "Test collection (*Selenium*)";
    public String blogName = "Test blog (*Selenium*)";
    public String blogPostName = "Test blog post (*Selenium*)";
    public String externalLinkName = "Test external link (*Selenium*)";
    public String proofingProjectName = "Test proofing project (*Selenium*)";
    public String submenuName = "Test submenu (*Selenium*)";
    public String storeName = "Test store (*Selenium*)";
    public String storeProductName = "Test store product (*Selenium*)";
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
        } else if (browser.equals(BrowserType.EDGE)) {
            File file = new File("C:/Tools/msedgedriver.exe");
            System.setProperty("webdriver.edge.driver", file.getAbsolutePath());
            wd = new EdgeDriver();
        } else if (browser.equals(BrowserType.OPERA_BLINK)) {
            wd = new OperaDriver();
        }

        wd.manage().window().setSize(dimension);
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.get("https://www.format.com/");
        menuEditorHelper = new MenuEditorHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        pageEditorHelper = new PageEditorHelper(wd);
        storeHelper = new StoreHelper(wd);
        blogEditorHelper = new BlogEditorHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login(new LoginData(login, password));
    }



    public void stop() {
        wd.quit();
    }

    public void pause(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MenuEditorHelper getMenuEditorHelper() { return menuEditorHelper; }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public PageEditorHelper getPageEditorHelper() { return pageEditorHelper; }

    public StoreHelper getStoreHelper() { return storeHelper; }

    public BlogEditorHelper getBlogEditorHelper() { return blogEditorHelper; }
}
