package ru.java_b34.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void goToHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public void goToContactPage() {
    if (isElementPresent(By.tagName("h1"))
            && (wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry"))
            && (isElementPresent(By.name("submit")))) {
      return;
    }
    click(By.linkText("add new"));
  }

  public void goToGroupPage() {
    if (isElementPresent(By.tagName("h1"))
            && (wd.findElement(By.tagName("h1")).getText().equals("Groups"))
            && (isElementPresent(By.name("new")))) {
      return;
    }
    click(By.linkText("groups"));
  }
}

