package de.tschuehly.modulitheclipseserializer;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.IncompleteEventPublications;
import org.springframework.modulith.events.core.EventPublicationRegistry;
import org.springframework.modulith.events.core.EventPublicationRepository;
import org.springframework.modulith.events.core.TargetEventPublication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RetryMechanism {

  private static final Logger log = LoggerFactory.getLogger(RetryMechanism.class);
  private final IncompleteEventPublications incompleteEventPublications;
  private final ApplicationEventPublisher applicationEventPublisher;
  private final EventPublicationRepository eventPublicationRepository;
  private final EventPublicationRegistry eventPublicationRegistry;

  public RetryMechanism(IncompleteEventPublications incompleteEventPublications,
      ApplicationEventPublisher applicationEventPublisher, EventPublicationRepository eventPublicationRepository,
      EventPublicationRegistry eventPublicationRegistry) {
    this.incompleteEventPublications = incompleteEventPublications;
    this.applicationEventPublisher = applicationEventPublisher;
    this.eventPublicationRepository = eventPublicationRepository;
    this.eventPublicationRegistry = eventPublicationRegistry;
  }

  @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
  public void retries() {
    completePublicationsOlderThan(Duration.ofSeconds(10));
    incompleteEventPublications.resubmitIncompletePublicationsOlderThan(Duration.ofSeconds(5));
  }

  private void completePublicationsOlderThan(Duration duration) {
    Collection<TargetEventPublication> incompletePublications = eventPublicationRegistry.findIncompletePublicationsOlderThan(
        duration);
    incompletePublications
        .forEach(it -> {
          log.info(it.getTargetIdentifier() + ": set completed");
          applicationEventPublisher.publishEvent(new ErrorHappened(it.getTargetIdentifier().getValue()));
          eventPublicationRepository.markCompleted(it.getEvent(),it.getTargetIdentifier(), Instant.now());
        });
  }

}
