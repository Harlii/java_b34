package ru.java_b34.addressbook.tests;

import org.testng.annotations.Test;
import ru.java_b34.addressbook.model.GroupData;
import ru.java_b34.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData("Work", "Work_logo", "Work_comment");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.withAdded(group.setId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
