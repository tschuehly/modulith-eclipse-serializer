package de.tschuehly.modulitheclipseserializer;

import de.tschuehly.modulitheclipseserializer.demo.TestDomain;
import de.tschuehly.modulitheclipseserializer.demo.TestDomain2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class ModulithEclipseSerializerApplicationTests {

  @Autowired
  ApplicationEventPublisher applicationEventPublisher;

  @Test
  void contextLoads() {
    applicationEventPublisher.publishEvent(new TestDomain2("domain2", new TestDomain("domain1")));
    applicationEventPublisher.publishEvent(new TestDomain2("domain3", new TestDomain("domain4")));
  }

}
