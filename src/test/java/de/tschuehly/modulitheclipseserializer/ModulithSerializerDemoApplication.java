package de.tschuehly.modulitheclipseserializer;

import de.tschuehly.modulitheclipseserializer.demo.TestDomain;
import de.tschuehly.modulitheclipseserializer.demo.TestDomain2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;


@Configuration
public class ModulithSerializerDemoApplication {

  public static void main(String[] args) {
    SpringApplication.from(ModulithEclipseSerializerApplication::main)
        .with(TestcontainersConfiguration.class).run(args);
  }

}
