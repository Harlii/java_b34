package ru.java_b34.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.ContactData;
import ru.java_b34.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    String group = "Work";
    if (app.contact().list().size() == 0) {
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
    List<ContactData> before = app.contact().list();
    int index = 0;
    ContactData contact = new ContactData()
            .withFirstname("Dmitrii_modification").withMiddlename("V_modification").withLastname("Kharlan_modification").withNickname("Harli_modification")
            .withTitle("Title_modification").withCompany("Company_modification").withAddress("Russia_modification").withHomenumber("89995554466")
            .withEmail("test_modification@gmail.com");
    app.contact().modify(contact, index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byLastName = (c1, c2) -> String.CASE_INSENSITIVE_ORDER.compare(c1.getLastname(), c2.getLastname());
    before.sort(byLastName);
    after.sort(byLastName);
    Assert.assertEquals(after, before);
  }
}
