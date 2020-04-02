package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeTest
    public void setUp() throws Exception {
        app.init();
    }

    @AfterTest
    public void tearDown() {
        app.stop();
    }

}
