package ru.java_b34.addressbook.appmanager;

import org.openqa.selenium.By;
import ru.java_b34.addressbook.model.GroupData;

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

  public void selectGroup() {
    click(By.name("selected[]"));
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

  public void createGroup(GroupData group) {
    manager.getNavigationHelper().goToGroupPage();
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    manager.getNavigationHelper().goToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public boolean isThereAGroup(String name) {
    return isElementPresent(By.linkText(name));
  }

  public void modificationGroup(GroupData group) {
    manager.getNavigationHelper().goToGroupPage();
    selectGroup();
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    manager.getNavigationHelper().goToGroupPage();
  }

  public int getGroupCount() {
    manager.getNavigationHelper().goToGroupPage();
    return wd.findElements(By.name("selected[]")).size();
  }
}
