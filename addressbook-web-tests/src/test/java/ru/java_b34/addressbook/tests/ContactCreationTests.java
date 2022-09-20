package ru.java_b34.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.ContactData;
import ru.java_b34.addressbook.model.Contacts;
import ru.java_b34.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  String group = "Work";

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (! app.group().isThereAGroup(group)) {
      app.group().create(new GroupData("Work", "Work_logo", "Work_comment"));
    }
  }

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    File photo = new File("src/test/resources/avatar.jpg");
    list.add(new Object[] {new ContactData().withFirstname("Dmitrii 1").withMiddlename("V").withLastname("Kharlan").withAddress("Russia").withGroup(group).withPhoto(photo)});
    list.add(new Object[] {new ContactData().withFirstname("Dmitrii 2").withMiddlename("V").withLastname("Kharlan").withAddress("Russia").withGroup(group).withPhoto(photo)});
    list.add(new Object[] {new ContactData().withFirstname("Dmitrii 3").withMiddlename("V").withLastname("Kharlan").withAddress("Russia").withGroup(group).withPhoto(photo)});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
