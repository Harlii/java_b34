package ru.java_b34.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.java_b34.addressbook.appmanager.ApplicationManager;

public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

  @BeforeSuite()
  public void setUp() {
    app.init();
  }

  @AfterSuite()
  public void tearDown() {
    app.stop();
  }

}
