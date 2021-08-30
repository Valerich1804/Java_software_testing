package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class DeleteContactTest extends TestBase{

    @Test
    public void testContactDeletion() {

        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Andrew", "Valerevich2", "Ivanov", "valerich1804", "NN Minina 12", "88991122", "valerich1804@yandex.ru", null), false);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(0);
        app.getContactHelper().deletContactButton();
        app.getContactHelper().allerClic();
        app.getContactHelper().sleep();
        app.getContactHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() -1);;

        before.remove(before.size() -1);
        Assert.assertEquals(before, after);
    }
}
