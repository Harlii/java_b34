package ru.java_b34.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.GroupData;

import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.HashSet;
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
    GroupData group = new GroupData(before.get(0).getId(), "Work_modification", "Work_logo_modification", "Work_comment_modification");
    app.getGroupHelper().modificationGroup(group, 0);
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(0);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }
}
