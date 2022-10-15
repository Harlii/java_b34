package ru.java_b34.mantis.appmanager;

import org.openqa.selenium.By;
import ru.java_b34.mantis.model.UserData;

public class ResetPasswordHelper extends HelperBase {

  public ResetPasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void loginAdmin(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));
  }

  public void start(UserData selectedUser) {
    click(By.cssSelector("a[href*='manage_overview_page.php']"));
    click(By.cssSelector("a[href*='manage_user_page.php']"));
    click(By.xpath("//a[.='" + selectedUser.getUsername() + "']"));
    click(By.cssSelector("input[value='Сбросить пароль']"));
  }

  public void finish(String confirmationLink, String newPassword) {
    wd.get(confirmationLink);
    type(By.id("password"), newPassword);
    type(By.id("password-confirm"), newPassword);
    click(By.cssSelector("button[type='submit']"));
  }
}
