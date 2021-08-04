package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactModificationTest extends TestBase{
    @Test
    public void testContactModification(){
        int before = app.getGroupHelper().getGroupCount();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Andrew", "Valerevich2", "Ivanov", "valerich1804", "NN Minina 12", "88991122", "valerich1804@yandex.ru", null), false);
        }
        app.getContactHelper().selectContact(0);
        app.getContactHelper().editContact();
        app.getContactHelper().feelContactForm(new ContactData("Andrew", "Valerevich2", "Ivanov", "valerich1804", "NN Minina 12", "88991122", "valerich1804@yandex.ru", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().gotoHomePage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before);
    }
}
