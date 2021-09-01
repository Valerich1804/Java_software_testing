package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void creation() {
        click(By.name("submit"));
    }

    public void feelForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("address"), contactData.getAddress());
        click(By.xpath("//div[@id='content']/form/label[9]"));
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobilephone());
        type(By.name("work"), contactData.getWorkphone());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.xpath(String.format("//input[@value = '%s']/../../td[8]/a", id))).click();
    }

    public void newContrct(ContactData contact, boolean creation) {
        addNewContact();
        feelForm(contact, true);
        creation();
        homePage();
    }

    public void edit(int id) {
        click(By.xpath(String.format("//input[@value = '%s']/../../td[8]/a", id)));
    }

    public void modification() {
        click(By.name("update"));
    }

    public void modification(ContactData contact) {
        edit(contact.getId());
        feelForm(contact, false);
        modification();
        contactCache = null;
        homePage();
    }

    public void homePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home page"));
    }

    public void create(ContactData contact, boolean b) {
        addNewContact();
        feelForm(contact, b);
        creation();
        contactCache = null;
        homePage();
        sleep();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deletContactButton();
//        allerClic();
        sleep();
        contactCache = null;
        homePage();
    }

    public int count() {
        return wd.findElements(By.xpath("//tr[@name = 'entry']")).size();
    }


    public void deletContactButton() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void allerClic() {
        Alert alert = wd.switchTo().alert();
        alert.accept();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        iniContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomephone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    private void iniContactModificationById(int id) {
        wd.findElement(By.xpath(String.format("//input[@value = '%s']/../../td[8]/a", id))).click();

    }

    public void sleep() {
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private Contacts contactCache = null;


    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for(WebElement row : rows) {
        List<WebElement> cells = row.findElements(By.tagName("td"));
        int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
        String lastname = cells.get(1).getText();
        String firstname = cells.get(2).getText();
        String allPhones = cells.get(5).getText();
        contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                .withAllPhones(allPhones));

    }
    return new Contacts(contactCache);
    }

}