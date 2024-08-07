package de.tschuehly.modulitheclipseserializer;

import de.tschuehly.modulitheclipseserializer.demo.TestDomain;
import de.tschuehly.modulitheclipseserializer.demo.TestDomain2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class ModulithEclipseSerializerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ModulithEclipseSerializerApplication.class, args);
  }

  @Controller
  static class EmptyController{
    private final ApplicationEventPublisher applicationEventPublisher;

    EmptyController(ApplicationEventPublisher applicationEventPublisher) {
      this.applicationEventPublisher = applicationEventPublisher;
    }

    @GetMapping
    @ResponseBody
    @Transactional
    public String  publishEvent(){
      applicationEventPublisher.publishEvent(new TestDomain2("domain2", new TestDomain("domain1")));
      return "published";
    }
  }

}
