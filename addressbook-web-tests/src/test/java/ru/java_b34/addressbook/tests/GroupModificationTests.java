package ru.java_b34.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    int before = app.getGroupHelper().getGroupCount();
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Work", "Work_logo", "Work_comment"));
      app.getNavigationHelper().goToGroupPage();
    }
    app.getGroupHelper().modificationGroup(new GroupData("Work_modification", "Work_logo_modification", "Work_comment_modification"));
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before);
  }
}
