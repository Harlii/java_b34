package ru.java_b34.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.ContactData;
import ru.java_b34.addressbook.model.Contacts;
import ru.java_b34.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    String group = "Work";
    File photo = new File("src/test/resources/avatar.jpg");
    if (app.db().contacts().size() == 0) {
      app.goTo().groupPage();
      if (! app.group().isThereAGroup(group)) {
        app.group().create(new GroupData(group, "Work_logo", "Work_comment"));
      }
      app.contact().create(new ContactData()
              .withFirstname("Dmitrii").withMiddlename("V").withLastname("Kharlan").withNickname("Harli").withTitle("Title").withCompany("Company")
              .withAddress("Russia").withHomePhone("89995554466").withMobilePhone("+7(999)-666-55-44").withWorkPhone("22-33-44")
              .withEmail("test@gmail.com").withEmail2("test_2@gmail.com").withEmail3("test_3@gmail.com").withPhoto(photo).withGroup(group));
    }
  }

  @Test
  public void testContactDeletionTests() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListInUI();
  }
}
