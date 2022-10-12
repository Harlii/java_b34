package ru.java_b34.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.ContactData;
import ru.java_b34.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    File photo = new File("src/test/resources/avatar.jpg");
    if (app.db().contacts().size() == 0) {
      app.goTo().groupPage();
      if (! app.group().isThereAGroup()) {
        app.group().create(new GroupData("Work", "Work_logo", "Work_comment"));
      }
      ContactData contact = new ContactData()
              .withFirstname("Dmitrii").withMiddlename("V").withLastname("Kharlan").withNickname("Harli").withTitle("Title").withCompany("Company")
              .withAddress("Russia").withHomePhone("89995554466").withMobilePhone("+7(999)-666-55-44").withWorkPhone("22-33-44")
              .withEmail("test@gmail.com").withEmail2("test_2@gmail.com").withEmail3("test_3@gmail.com").withPhoto(photo);
      app.contact().addToGroup(contact);
    }
  }

  @Test
  public void testDeleteContactFromGroup() {
    app.goTo().homePage();
    GroupData randomGroup = app.db().groups().iterator().next();
    int contactsBefore = randomGroup.getContacts().size();
    if (randomGroup.getContacts().size() > 0) {
      app.contact().deleteFromGroup(randomGroup);
    } else {
      //если в рандомной группе нет контактов, то добавляем рандомный контакт в рандомную группу
      ContactData randomContact = app.db().contacts().iterator().next();
      app.contact().selectContactById(randomContact.getId());
      app.contact().selectDropDown(By.name("to_group"), randomGroup.getName());
      app.contact().confirmAddToGroup();
      app.goTo().homePage();

      //переопределяем рандомную группу, т.к. в нее был добавлен контакт. Производим удаление группы
      randomGroup = app.db().getGrouptById(randomGroup.getId());
      contactsBefore = app.db().getGrouptById(randomGroup.getId()).getContacts().size();
      app.contact().deleteFromGroup(randomGroup);
    }
    int contactsAfter = app.db().getGrouptById(randomGroup.getId()).getContacts().size();
    assertThat(contactsAfter, equalTo(contactsBefore - 1));
  }

}
