package ru.java_b34.addressbook.tests;

import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Dmitrii", "V", "Kharlan", "Harli", "Title", "Company", "Russia", "89995554466", "test@gmail.com", "Work"), true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().goToHomePage();
  }
}
