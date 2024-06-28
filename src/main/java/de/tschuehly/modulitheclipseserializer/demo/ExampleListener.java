package de.tschuehly.modulitheclipseserializer.demo;

import de.tschuehly.modulitheclipseserializer.ErrorHappened;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
class ExampleListener {

  private Integer counter = 0;

  @ApplicationModuleListener
  public void handleTestDomain2Event(TestDomain2 event) {
    System.out.println("Received event: " + event.name());
    counter = counter + 1;
    if (counter == 2) {
      throw new RuntimeException("Some Error occured");
    }
  }


  @ApplicationModuleListener
  public void handleErrorHappened(ErrorHappened event) {
    System.out.println("Handled error: " + event.targetIdentifier);
  }


}