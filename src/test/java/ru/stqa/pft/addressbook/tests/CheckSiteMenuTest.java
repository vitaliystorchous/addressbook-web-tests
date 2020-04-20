package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Items;
import ru.stqa.pft.addressbook.model.Link;
import ru.stqa.pft.addressbook.model.MenuEditorItem;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CheckSiteMenuTest extends TestBase {

    @Test
    public void test() {
        app.goTo().pagesPage();
        List<String> itemsNames = app.menuEditor().itemsInMenu().stream().map(MenuEditorItem::getName).collect(toList());
        List<String> linksNames = app.menuEditor().previewBloc().siteMenuLinks().stream().map(Link::getName).collect(toList());
        itemsNames.sort(comparing(String::toString));
        linksNames.sort(comparing(String::toString));
        assertThat(linksNames, equalTo(itemsNames));
    }
}
