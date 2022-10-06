package ru.java_b34.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.ContactData;
import ru.java_b34.addressbook.model.Contacts;
import ru.java_b34.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  File photo = new File("src/test/resources/avatar.jpg");

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.goTo().groupPage();
      if (! app.group().isThereAGroup()) {
        app.group().create(new GroupData("Work", "Work_logo", "Work_comment"));
      }
      app.contact().create(new ContactData()
              .withFirstname("Dmitrii").withMiddlename("V").withLastname("Kharlan").withNickname("Harli").withTitle("Title").withCompany("Company")
              .withAddress("Russia").withHomePhone("89995554466").withMobilePhone("+7(999)-666-55-44").withWorkPhone("22-33-44")
              .withEmail("test@gmail.com").withEmail2("test_2@gmail.com").withEmail3("test_3@gmail.com").withPhoto(photo));
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Dmitrii_modification").withMiddlename("V_modification").withLastname("Kharlan_modification")
            .withNickname("Harli_modification").withTitle("Title_modification").withCompany("Company_modification").withAddress("Russia_modification")
            .withHomePhone("89995554466").withMobilePhone("+7(999)-666-55-44").withWorkPhone("22-33-44").withEmail("test_modification@gmail.com")
            .withEmail2("test_modification_2@gmail.com").withEmail3("test_modification_3@gmail.com").withPhoto(photo);
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }
}
