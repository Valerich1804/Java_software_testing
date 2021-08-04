package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void feelContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("address"), contactData.getAddress());
        click(By.xpath("//div[@id='content']/form/label[9]"));
        type(By.name("home"), contactData.getHomephone());
        type(By.name("email"), contactData.getEmail());

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }
    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void editContact() {
        click(By.xpath("//img[@alt='Edit']"));
    }
    public void submitContactModification() {
        click(By.name("update"));
    }
    public void gotoHomePage() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home page"));
    }

    public void createContact(ContactData contact, boolean b) {
        addNewContact();
        feelContactForm(new ContactData("Andrew", "Valerevich", "Ivanov", "valerich1804", "NN Minina 12", "88991122", "valerich1804@yandex.ru", "test1"), true);
        submitContactCreation();
        gotoHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void deletContactButton() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void allerClic() {
        Alert alert = wd.switchTo().alert();
        alert.accept();
    }
    public void sleep(){
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
