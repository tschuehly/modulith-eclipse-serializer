package de.tschuehly.modulitheclipseserializer.demo;

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
  }