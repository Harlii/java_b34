package ru.java_b34.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void goToHomePage() {
    click(By.linkText("home"));
  }

  public void goToContactPage() {
    click(By.linkText("add new"));
  }

  public void goToGroupPage() {
    click(By.linkText("groups"));
  }
}


