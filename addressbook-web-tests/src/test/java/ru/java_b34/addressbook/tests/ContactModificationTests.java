package ru.java_b34.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.ContactData;
import ru.java_b34.addressbook.model.GroupData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    String group = "Work";
    if (app.contact().all().size() == 0) {
      app.goTo().groupPage();
      if (! app.group().isThereAGroup(group)) {
        app.group().create(new GroupData("Work", "Work_logo", "Work_comment"));
      }
      app.contact().create(new ContactData()
              .withFirstname("Dmitrii").withMiddlename("V").withLastname("Kharlan").withNickname("Harli").withTitle("Title").withCompany("Company")
              .withAddress("Russia").withHomenumber("89995554466").withEmail("test@gmail.com").withGroup(group));
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Dmitrii_modification").withMiddlename("V_modification").withLastname("Kharlan_modification")
            .withNickname("Harli_modification").withTitle("Title_modification").withCompany("Company_modification").withAddress("Russia_modification")
            .withHomenumber("89995554466").withEmail("test_modification@gmail.com");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(after, before);
  }
}
