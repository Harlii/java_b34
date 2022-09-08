package ru.java_b34.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.ContactData;
import ru.java_b34.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletionTests() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().goToGroupPage();
      if (! app.getGroupHelper().isThereAGroup("Work")) {
        app.getGroupHelper().createGroup(new GroupData("Work", "Work_logo", "Work_comment"));
      }
      app.getContactHelper().createContact(new ContactData("Dmitrii", "V", "Kharlan", "Harli", "Title", "Company", "Russia", "89995554466", "test@gmail.com", "Work"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact(0);
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(0);
    Assert.assertEquals(after, before);
  }
}
