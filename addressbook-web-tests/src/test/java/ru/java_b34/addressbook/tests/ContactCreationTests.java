package ru.java_b34.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.ContactData;
import ru.java_b34.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup("Work")) {
      app.getGroupHelper().createGroup(new GroupData("Work", "Work_logo", "Work_comment"));
    }
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new ContactData("Test", "V", "Testovich", "Harli", "Title", "Company", "Russia", "89995554466", "test@gmail.com", "Work"));
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    after.remove(after.size() - 1);
    Assert.assertEquals(after, before);
  }
}
