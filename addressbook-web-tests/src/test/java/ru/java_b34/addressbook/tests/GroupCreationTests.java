package ru.java_b34.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  WebDriver wd;

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("Test3", "Work_logo", "Work_comment");
    app.getGroupHelper().createGroup(group);
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }
}
