package ru.java_b34.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Work", "Work_logo", "Work_comment"));
      app.getNavigationHelper().goToGroupPage();
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().modificationGroup(new GroupData("Work_modification", "Work_logo_modification", "Work_comment_modification"), 0);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    Assert.assertEquals(after, before);
  }
}
