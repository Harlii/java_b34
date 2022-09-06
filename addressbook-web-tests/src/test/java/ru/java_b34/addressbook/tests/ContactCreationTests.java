package ru.java_b34.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.ContactData;
import ru.java_b34.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    int before = app.getContactHelper().getContactCount();
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup("Work")) {
      app.getGroupHelper().createGroup(new GroupData("Work", "Work_logo", "Work_comment"));
    }
    app.getContactHelper().createContact(new ContactData("Dmitrii", "V", "Kharlan", "Harli", "Title", "Company", "Russia", "89995554466", "test@gmail.com", "Work"));
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }
}
