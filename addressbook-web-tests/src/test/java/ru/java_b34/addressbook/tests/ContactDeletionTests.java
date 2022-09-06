package ru.java_b34.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.ContactData;
import ru.java_b34.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletionTests() {
    int before = app.getContactHelper().getContactCount();
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().goToGroupPage();
      if (! app.getGroupHelper().isThereAGroup("Work")) {
        app.getGroupHelper().createGroup(new GroupData("Work", "Work_logo", "Work_comment"));
      }
      app.getContactHelper().createContact(new ContactData("Dmitrii", "V", "Kharlan", "Harli", "Title", "Company", "Russia", "89995554466", "test@gmail.com", "Work"));
    }
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact(0);
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().goToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }
}
