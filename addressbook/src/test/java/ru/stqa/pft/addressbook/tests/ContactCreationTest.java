package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTest extends TestBase {

  @Test
  public void testUntitledTestCase() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("test2").withMiddlename("Valerevich").withLastname("Ivanov")
            .withNickname("valerich1804").withAddress("NN Minina 12").withHomephone("88991122").withEmail("valerich1804@yandex.ru")
            .withGroup("test1");
    app.contact().newContrct(contact, true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}