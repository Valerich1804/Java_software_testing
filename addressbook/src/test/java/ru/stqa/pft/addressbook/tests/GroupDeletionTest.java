package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.gotoGroupePage();
    app.selectGroup();
    app.deleteSelectedGroup();
    app.returntoGroupPage();
  }

}
