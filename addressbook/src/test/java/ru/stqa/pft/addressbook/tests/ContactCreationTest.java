package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testUntitledTestCase() {
    app.getContactHelper().addNewContact();
    app.getContactHelper().feelContactForm(new ContactData("Andrew", "Valerevich", "Ivanov", "valerich1804", "NN Minina 12", "88991122", "valerich1804@yandex.ru", "test1"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().gotoHomePage();

  }
}