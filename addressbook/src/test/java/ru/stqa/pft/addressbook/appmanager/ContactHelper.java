package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.ArrayList;
import java.util.List;
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

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
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

        public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
        List <WebElement> cellsLasttname = wd.findElements(By.xpath("//tr[@name = 'entry']/td[2]"));
        List <WebElement> cellsFirstname = wd.findElements(By.xpath("//tr[@name = 'entry']/td[3]"));
        for (int i = 0; i < elements.size() ; i++) {
            String lastname = cellsLasttname.get(i).getText();
            String firstname = cellsFirstname.get(i).getText();
            int id = Integer.parseInt(elements.get(i).findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData(id, firstname, null, lastname, null, null, null, null, null);
            contacts.add(contact);
            /*for (WebElement element: elements) {
            String firstname = element.getText();
            String lastname = element.getText();
            int id = Integer.parseInt(element.findElement(By.xpath("//td/input")).getAttribute("value"));
            ContactData contact = new ContactData(id, firstname, null, lastname, null, null, null, null, null);
            contacts.add(contact);*/
        }

        return contacts;
    }
}
