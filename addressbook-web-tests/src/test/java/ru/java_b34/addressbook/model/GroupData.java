package ru.java_b34.addressbook.model;

import java.util.Objects;

public class GroupData {
  private final String name;
  private final String header;
  private final String footer;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData data = (GroupData) o;

    return Objects.equals(name, data.name);
  }

  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "name='" + name + '\'' +
            '}';
  }

  public GroupData(String name, String header, String footer) {
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }
}
