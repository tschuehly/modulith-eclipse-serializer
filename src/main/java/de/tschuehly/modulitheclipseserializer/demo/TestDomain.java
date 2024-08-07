package de.tschuehly.modulitheclipseserializer.demo;

import java.util.Objects;

public final class TestDomain {

    private final String name;

    public TestDomain(String name) {
      this.name = name;
    }

    public String name() {
      return name;
    }
    @Override
    public String toString() {
      return "TestDomain[" +
             "name=" + name + ']';
    }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TestDomain that = (TestDomain) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name);
  }
}