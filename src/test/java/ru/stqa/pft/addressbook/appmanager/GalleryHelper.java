package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class GalleryHelper extends HelperBase {

    public GalleryHelper(WebDriver wd) {
        super(wd);
    }

    public void addImage(File image) {
        click(By.xpath("//button[@class=\"btn btn-primarycolor btn-outline add-item-button\"]"));
        click(By.xpath("//li[@class=\"add-item-dropdown-list-item add_image\"]"));
        wait.until(visibilityOfElementLocated(By.xpath("//div[@id='vfs_gateway']//button[@class='btn btn-primarycolor']")));
        attach(By.xpath("//input[@class=\"cloudinary-fileupload\"]"), image);
        pause(1);
        wait.until(presenceOfElementLocated(By.xpath("//a[@class=\"btn btn-small control_button btn-primarycolor\"]")));
        click(By.xpath("//a[@class=\"btn btn-small control_button btn-primarycolor\"]"));
        click(By.id("vfs_close"));
        WebElement saveButton = wd.findElement(By.xpath("//button[contains(@class, 'btn-save')]"));
        saveButton.click();
        pause(1);
        wait.until(textToBePresentInElement(saveButton, "Saved"));
    }
}
