package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactModificationTest extends TestBase{

    @BeforeMethod
    public void ensurePrecondition(){
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData().withFirstname("Andrew").withMiddlename("Valerevich1").withLastname("Ivanov")
                    .withNickname("valerich1804").withAddress("NN Minina 12").withHomephone("88991122").withEmail("valerich1804@yandex.ru")
                    .withGroup("test1"), false);
        }
    }
    @Test
    public void testContactModification(){

        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Andrew").withMiddlename("Valerevich").withLastname("Ivanov")
                .withNickname("valerich1804").withAddress("NN Minina 12").withHomephone("88991122")
                .withMobilePhone("88991122").withWorkPhone("121211212").withEmail("valerich1804@yandex.ru").withGroup("test1");
        app.contact().modification(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    }

}
