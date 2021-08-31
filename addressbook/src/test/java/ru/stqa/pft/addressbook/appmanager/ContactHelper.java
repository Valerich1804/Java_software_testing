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

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id ='" + id + "']")).click();
    }

    public void newContrct(ContactData contact,boolean creation) {
        addNewContact();
        feelForm(contact, true);
        creation();
        homePage();
    }

    public void edit() {
        click(By.xpath("//img[@alt='Edit']"));
    }
    public void modificationContract() {
        click(By.name("update"));
    }

    public void modificationContract(ContactData contact) {
        selectContactById(contact.getId());
        edit();
        feelForm(contact, false);
        modificationContract();
        homePage();
    }
    public void homePage() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home page"));
    }

    public void create(ContactData contact, boolean b) {
        addNewContact();
        feelForm(contact, b);
        creation();
        homePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deletContactButton();
        allerClic();
        sleep();
        homePage();
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

        public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
        List <WebElement> cellsLasttname = wd.findElements(By.xpath("//tr[@name = 'entry']/td[2]"));
        List <WebElement> cellsFirstname = wd.findElements(By.xpath("//tr[@name = 'entry']/td[3]"));
        for (int i = 0; i < elements.size() ; i++) {
            String lastname = cellsLasttname.get(i).getText();
            String firstname = cellsFirstname.get(i).getText();
            int id = Integer.parseInt(elements.get(i).findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withMiddlename("Valerevich1").withLastname(lastname)
                    .withNickname("valerich1804").withAddress("NN Minina 12").withHomephone("88991122").withEmail("valerich1804@yandex.ru")
                    .withGroup("test1");
            contacts.add(contact);
        }

        return contacts;
    }
}
