package ru.java_b34.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.ContactData;
import ru.java_b34.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    int before = app.getContactHelper().getContactCount();
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().goToGroupPage();
      if (! app.getGroupHelper().isThereAGroup("Work")) {
        app.getGroupHelper().createGroup(new GroupData("Work", "Work_logo", "Work_comment"));
      }
      app.getContactHelper().createContact(new ContactData("Dmitrii", "V", "Kharlan", "Harli", "Title", "Company", "Russia", "89995554466", "test@gmail.com", "Work"));
    }
    app.getContactHelper().modificationContact(new ContactData("Dmitrii_modification", "V_modification", "Kharlan_modification", "Harli_modification", "Title_modification", "Company_modification", "Russia_modification", "89995554466", "test_modification@gmail.com", null), 0);
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
