package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.Link;

import java.util.ArrayList;
import java.util.List;

public class PreviewBlockHelper extends HelperBase {
    public PreviewBlockHelper(WebDriver wd) {
        super(wd);
    }

    public List<Link> siteMenuLinks() {
        switchFrame(wd.findElement(By.xpath("//iframe[@id='preview']")));
        List<WebElement> elements = wd.findElements(By.xpath("//nav[@class='menu']/ul//a"));
        List<Link> links = new ArrayList<>();
        for (WebElement element : elements) {
            links.add(new Link().withName(element.getAttribute("innerText")).withUrl(element.getAttribute("href")));
        }
        switchToBaseFrame();
        return links;
    }
}
