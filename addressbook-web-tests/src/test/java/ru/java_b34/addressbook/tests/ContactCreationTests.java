package ru.java_b34.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.ContactData;
import ru.java_b34.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {
  String group = "Work";

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (! app.group().isThereAGroup(group)) {
      app.group().create(new GroupData("Work", "Work_logo", "Work_comment"));
    }
  }

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData()
            .withFirstname("Dmitrii").withMiddlename("V").withLastname("Kharlan").withNickname("Harli").withTitle("Title").withCompany("Company")
            .withAddress("Russia").withHomenumber("89995554466").withEmail("test@gmail.com").withGroup(group);
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byLastName = (c1, c2) -> String.CASE_INSENSITIVE_ORDER.compare(c1.getLastname(), c2.getLastname());
    before.sort(byLastName);
    after.sort(byLastName);
    Assert.assertEquals(after, before);
  }
}
