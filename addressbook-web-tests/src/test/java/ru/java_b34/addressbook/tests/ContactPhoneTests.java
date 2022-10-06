package ru.java_b34.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.ContactData;
import ru.java_b34.addressbook.model.GroupData;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

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
              .withAddress("Russia, str. Kosmonavtov\n33-10").withHomePhone("+ 7 (999) 666 33 22").withMobilePhone("22-22-22").withWorkPhone("33 33 33")
              .withEmail("test@gmail.com").withEmail2("test_2@gmail.com").withEmail3("test_3@gmail.com").withPhoto(photo));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    verifyContactListInUI();
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter(s -> ! s.equals(""))
            .map(ContactPhoneTests::cleanedPhone)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter(s -> ! s.equals(""))
            .map(ContactPhoneTests::cleanedEmail)
            .collect(Collectors.joining("\n"));
  }

  public static String cleanedPhone(String phone) {
   return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  public static String cleanedEmail(String email) {
   return email.replaceAll("\\s", "");
  }
}
