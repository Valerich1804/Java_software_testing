package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactModificationTest extends TestBase{
    @Test
    public void testContactModification(){
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Andrew", "Valerevich2", "Ivanov", "valerich1804", "NN Minina 12", "88991122", "valerich1804@yandex.ru", null), false);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().editContact();
        app.getContactHelper().feelContactForm(new ContactData("Andrew", "Valerevich2", "Ivanov", "valerich1804", "NN Minina 12", "88991122", "valerich1804@yandex.ru", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().gotoHomePage();
    }
}
