package de.tschuehly.modulitheclipseserializer.demo;

import java.util.Objects;

public class TestDomain2 {

    private final String name;
    private final TestDomain domain;

    public TestDomain2(String name, TestDomain domain) {
      this.name = name;
      this.domain = domain;
    }

    public String name() {
      return name;
    }

    public TestDomain domain() {
      return domain;
    }

    @Override
    public String toString() {
      return "TestDomain2[" +
             "name=" + name + ", " +
             "domain=" + domain + ']';
    }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TestDomain2 that = (TestDomain2) o;
    return Objects.equals(name, that.name) && Objects.equals(domain, that.domain);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, domain);
  }
}