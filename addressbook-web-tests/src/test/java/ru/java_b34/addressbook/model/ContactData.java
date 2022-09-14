package ru.java_b34.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private String firstName;
  private String middleName;
  private String lastName;
  private String nickname;
  private String title;
  private String company;
  private String address;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;
  private String email;
  private String group;

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstName = firstname;
    return this;
  }

  public ContactData withMiddlename(String middlename) {
    this.middleName = middlename;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastName = lastname;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomePhone(String homenumber) {
    this.homePhone = homenumber;
    return this;
  }

  public ContactData withMobilePhone(String mobilephone) {
    this.mobilePhone = mobilephone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstName + '\'' +
            ", lastname='" + lastName + '\'' +
            ", homenumber='" + homePhone + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (!Objects.equals(firstName, that.firstName)) return false;
    if (!Objects.equals(lastName, that.lastName)) return false;
    if (!Objects.equals(homePhone, that.homePhone)) return false;
    return Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    return result;
  }
}
