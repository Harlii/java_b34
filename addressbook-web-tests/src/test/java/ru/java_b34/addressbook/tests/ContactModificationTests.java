package ru.java_b34.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.ContactData;
import ru.java_b34.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().goToGroupPage();
      if (! app.getGroupHelper().isThereAGroup("Work")) {
        app.getGroupHelper().createGroup(new GroupData("Work", "Work_logo", "Work_comment"));
      }
      app.getContactHelper().createContact(new ContactData("Dmitrii", "V", "Kharlan", "Harli", "Title", "Company", "Russia", "89995554466", "test@gmail.com", "Work"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Dmitrii_modification", "V_modification", "West", "Harli_modification", "Title_modification", "Company_modification", "Russia_modification", "89995554466", "test_modification@gmail.com", null);
    app.getContactHelper().modificationContact(contact, 1);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(1);
    before.add(contact);
    Comparator<? super ContactData> byLastName = (c1, c2) -> String.CASE_INSENSITIVE_ORDER.compare(c1.getLastname(), c2.getLastname());
    before.sort(byLastName);
    after.sort(byLastName);
    Assert.assertEquals(after, before);
  }
}
