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
    wd.findElement(By.cssSelector("input[value='Вход']")).click();
    type(By.name("password"), password);
    wd.findElement(By.cssSelector("input[value='Вход']")).click();
  }

  public void start(UserData selectedUser) {
    wd.findElement(By.cssSelector("a[href='manager_overview_page.php']")).click();
    wd.findElement(By.cssSelector("a[href='manager_user_page.php']")).click();
    click(By.xpath("//a[text()='" + selectedUser.withId(selectedUser.getId()) + "']"));
    wd.findElement(By.cssSelector("input[value='Сбросить пароль']")).click();
  }

  public void finish(String confirmationLink, String newPassword) {
    wd.get(confirmationLink);
    type(By.name("password"), newPassword);
    type(By.name("password_confirm"), newPassword);
    click(By.xpath("//*[@id=\"account-update-form\"]/fieldset/span/button/span['Изменить пользователя']"));
  }
}
