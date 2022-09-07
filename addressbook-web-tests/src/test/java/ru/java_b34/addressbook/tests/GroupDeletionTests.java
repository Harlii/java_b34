package ru.java_b34.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletionTests() {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Work", "Work_logo", "Work_comment"));
      app.getNavigationHelper().goToGroupPage();
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(0);
    app.getGroupHelper().deleteSelectedGroup();
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(0);
    Assert.assertEquals(after, before);
  }
}
