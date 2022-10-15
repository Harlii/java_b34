package ru.java_b34.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RegistrationHelper extends HelperBase {

  public RegistrationHelper (ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[type='submit']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("button[type='submit']"));
  }

  public void goToManageUserPage(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[value='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[value='submit']"));
    click(By.cssSelector("a[href='manage_overview_page.php']"));
    click(By.cssSelector("a[href='manage_user_page.php']"));
  }

  public void selectUser() {
    List<WebElement> rows = wd.findElements(By.cssSelector("tr[class^=row]"));
    for (int i = 1; i < rows.size(); i++) {
      List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
      String userName = cells.get(0).getText();
      if (!userName.equals("administrator")) {
        cells.get(0).findElement(By.tagName("a")).click();
        return;
      }
    }
  }

}
