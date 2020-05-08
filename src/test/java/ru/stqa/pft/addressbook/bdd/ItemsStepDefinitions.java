package ru.stqa.pft.addressbook.bdd;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.model.MenuEditorItem.Type.CUSTOM_PAGE;

public class ItemsStepDefinitions {

    private ApplicationManager app;
    private Items items;
    private MenuEditorItem newCustomPage;

    @Before
    public void init() throws IOException {
        app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
        app.init();
    }

    @After
    public void stop() {
        app.stop();
        app = null;
    }

    @Given("^a set of menu editor items$")
    public void loadItems() {
        app.goTo().pagesPage();
        items = app.menuEditor().allItems();
    }

    @When("^I create a new custom page with name (.+)$")
    public void createCustomPage(String name) {
        newCustomPage = new MenuEditorItem().withType(CUSTOM_PAGE).withName(name);
        app.menuEditor().createItem(newCustomPage);
    }

    @Then("^the new set of items is equal to the old set with the added custom page$")
    public void verifyCustomPageCreated() {
        Items newItems = app.menuEditor().allItems();
        assertThat(newItems, equalTo(items.withAdded(newCustomPage.withDataId(newItems.stream().mapToInt(MenuEditorItem::getDataId).max().getAsInt()))));
    }
}
