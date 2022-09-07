package ru.java_b34.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

  WebDriver wd;

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(new GroupData("Work", "Work_logo", "Work_comment"));
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    after.remove(after.size() - 1);
    Assert.assertEquals(after, before);

    //доработать упорядочивание
  }
}
