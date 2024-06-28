package de.tschuehly.modulitheclipseserializer.demo;

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
  }