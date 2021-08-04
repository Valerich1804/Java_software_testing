package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class DeleteContactTest extends TestBase{

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Andrew", "Valerevich2", "Ivanov", "valerich1804", "NN Minina 12", "88991122", "valerich1804@yandex.ru", null), false);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deletContactButton();
        app.getContactHelper().allerClic();
        app.getContactHelper().sleep();
        app.getContactHelper().gotoHomePage();
    }
}
