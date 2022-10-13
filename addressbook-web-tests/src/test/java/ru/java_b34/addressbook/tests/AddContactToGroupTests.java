package ru.java_b34.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.ContactData;
import ru.java_b34.addressbook.model.GroupData;
import ru.java_b34.addressbook.model.Groups;

import java.io.File;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    File photo = new File("src/test/resources/avatar.jpg");
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
  public void testAddContactToGroup() {
    app.goTo().homePage();
    app.contact().selectGroup("[all]");
    Groups allGroups = app.db().groups();
    System.out.println(allGroups);
//    ContactData randomContact = app.db().contacts().iterator().next();
//    int groupsBefore = randomContact.getGroups().size();
//    if (randomContact.getGroups().size() < allGroups.size()) {
//      app.contact().addToGroup(randomContact);
//    } else {
//      //если рандомный контакт добавлен во все группы, то добавляем его в новую созданную группу
//      GroupData newGroup = new GroupData("New group " + new Date(), "New header", "New footer");
//      app.group().create(newGroup);
//      app.goTo().homePage();
//      app.contact().addToGroup(randomContact);
//    }
//    int groupsAfter = app.db().getContactById(randomContact.getId()).getGroups().size();
//    assertThat(groupsAfter, equalTo(groupsBefore + 1));
  }

}
