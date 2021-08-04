package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase{
    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupePage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().feelGroupForm(new GroupData("test4", null, null));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returntoGroupPage();

    }
}
