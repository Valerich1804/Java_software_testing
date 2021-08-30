package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class ContactModificationTest extends TestBase{
    @Test
    public void testContactModification(){

        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Andrew", "Valerevich2", "Ivanov", "valerich1804", "NN Minina 12", "88991122", "valerich1804@yandex.ru", null), false);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() -1);
        app.getContactHelper().editContact();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Andrew", "Valerevich2", "Ivanov", "valerich1804", "NN Minina 12", "88991122", "valerich1804@yandex.ru", null);
        app.getContactHelper().feelContactForm((contact), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() -1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
