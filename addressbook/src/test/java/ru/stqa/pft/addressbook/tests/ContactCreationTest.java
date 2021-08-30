package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testUntitledTestCase() {
    List<ContactData> before = app.contact().list();
    app.contact().newContrct();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);
  }

}