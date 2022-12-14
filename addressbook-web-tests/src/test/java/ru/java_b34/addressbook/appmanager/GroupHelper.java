package ru.java_b34.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.java_b34.addressbook.model.GroupData;
import ru.java_b34.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase {

  public ApplicationManager manager;

  public GroupHelper(ApplicationManager manager) {
    super(manager.wd);
    this.manager = manager;
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  private void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedGroup() {
    click(By.name("delete"));
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) {
    manager.goTo().groupPage();
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupsCache = null;
    manager.goTo().groupPage();
  }

  public void delete(GroupData group) {
    manager.goTo().groupPage();
    selectGroupById(group.getId());
    deleteSelectedGroup();
    groupsCache = null;
    manager.goTo().groupPage();
  }

  public void modify(GroupData group) {
    manager.goTo().groupPage();
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupsCache = null;
    manager.goTo().groupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public boolean isThereAGroup(String group) {
    return isElementPresent(By.xpath("//span[.='" + group + "']"));
  }

  public int count () {
    manager.goTo().groupPage();
    return wd.findElements(By.name("selected[]")).size();
  }

  private Groups groupsCache = null;

  public Groups all() {
    if (groupsCache != null) {
      return new Groups(groupsCache);
    }
    groupsCache = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements ) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData(id, name, null, null);
      groupsCache.add(group);
    }
    return new Groups(groupsCache);
  }

}
