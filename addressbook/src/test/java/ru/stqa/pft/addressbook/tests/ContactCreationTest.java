package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testUntitledTestCase() {
    app.getNavigationHelper().addNewContact();
    app.getContactHelper().feelContactForm(new ContactData("Andrew", "Valerevich", "Ivanov", "valerich1804", "NN Minina 12", "88991122", "valerich1804@yandex.ru"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHomePage();

  }
}