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
    if (counter % 3 == 0) {
      throw new RuntimeException("Some Error occured");
    }
  }

// Cannot use ApplicationModuleListener here? It only triggers after 10 seconds and leads to an infinite Loop
  @EventListener
  public void handleErrorHappened(ErrorHappened event) {
    System.out.println("Handled error: " + event.targetIdentifier);
  }


}