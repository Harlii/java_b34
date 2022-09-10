package ru.java_b34.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData("Work", "Work_logo", "Work_comment"));
    }
  }

  @Test
  public void testGroupDeletionTests() {
    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedGroup);
    Assert.assertEquals(after, before);
  }
}
