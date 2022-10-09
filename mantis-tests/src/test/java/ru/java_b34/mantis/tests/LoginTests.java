package ru.java_b34.mantis.tests;

import org.testng.annotations.Test;
import ru.java_b34.mantis.appmanager.HttpSession;

import static org.testng.Assert.assertTrue;

public class LoginTests extends  TestBase {

  @Test
  public void testLogin() throws Exception {
    HttpSession session = app.newSession();
    assertTrue(session.login("administrator","root"));
    assertTrue(session.isLoggedInAs("administrator"));
  }

}
