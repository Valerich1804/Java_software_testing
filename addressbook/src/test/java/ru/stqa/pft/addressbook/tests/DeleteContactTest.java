package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class DeleteContactTest extends TestBase{
    @BeforeMethod
    public void ensurePrecondition(){
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData().withFirstname("Andrew").withMiddlename("Valerevich1").withLastname("Ivanov")
                    .withNickname("valerich1804").withAddress("NN Minina 12").withHomephone("88991122").withEmail("valerich1804@yandex.ru")
                    .withGroup("test1"), false);        }
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() -1);;

        assertThat(after, equalTo(before.without(deletedContact)));
    }

}
