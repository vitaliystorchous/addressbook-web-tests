package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    private SessionHelper sessionHelper;
    private WebDriver wd;
    private NavigationHelper navigationHelper;
    private MenuEditorHelper menuEditorHelper;
    private StoreHelper storeHelper;
    private BlogEditorHelper blogEditorHelper;
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
        properties = new Properties();

    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if ("".equals(properties.getProperty("selenium.server"))) {
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
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            //capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "win10")));
            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }

        wd.manage().window().setSize(dimension);
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        menuEditorHelper = new MenuEditorHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        pageEditorHelper = new PageEditorHelper(wd);
        storeHelper = new StoreHelper(wd);
        blogEditorHelper = new BlogEditorHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login(properties.getProperty("web.login"), properties.getProperty("web.password"));
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

    public MenuEditorHelper menuEditor() { return menuEditorHelper; }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public PageEditorHelper pageEditor() { return pageEditorHelper; }

    public StoreHelper getStoreHelper() { return storeHelper; }

    public BlogEditorHelper getBlogEditorHelper() { return blogEditorHelper; }
}
