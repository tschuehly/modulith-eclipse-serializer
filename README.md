# SPRING MODULITH ECLIPSE SERIALIZER

To show the current issues you need to enable testcontainers reuse as true in testcontainers.properties

`testcontainers.reuse.enable=true`


Current Issues:

After restarting, Eclipse cannot load deserialize the event

```shell
java.lang.NullPointerException: Cannot invoke "org.eclipse.serializer.collections.types.XGettingCollection.iterator()" because the return value of "org.eclipse.serializer.collections.BulkList.last()" is null
	at org.eclipse.serializer.persistence.binary.types.BinaryLoader$Default.addChunks(BinaryLoader.java:801) ~[persistence-binary-1.3.2.jar:na]
	at org.eclipse.serializer.persistence.binary.types.BinaryLoader$Default.readLoadOidData(BinaryLoader.java:774) ~[persistence-binary-1.3.2.jar:na]
	at org.eclipse.serializer.persistence.binary.types.BinaryLoader$Default.readLoadOnce(BinaryLoader.java:718) ~[persistence-binary-1.3.2.jar:na]
	at org.eclipse.serializer.persistence.binary.types.BinaryLoader$Default.get(BinaryLoader.java:827) ~[persistence-binary-1.3.2.jar:na]
	at org.eclipse.serializer.persistence.types.PersistenceManager$Default.get(PersistenceManager.java:398) ~[persistence-1.3.2.jar:na]
	at org.eclipse.serializer.TypedSerializer$Default.deserialize(TypedSerializer.java:336) ~[serializer-1.3.2.jar:na]
	at de.tschuehly.modulitheclipseserializer.EclipseEventSerializer.deserialize(EclipseEventSerializer.java:26) ~[main/:na]
	at de.tschuehly.modulitheclipseserializer.JdbcEventPublicationRepository$JdbcEventPublication.getEvent(JdbcEventPublicationRepository.java:414) ~[main/:na]
	at de.tschuehly.modulitheclipseserializer.RetryMechanism.lambda$completePublicationsOlderThan$0(RetryMechanism.java:48) ~[main/:na]
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597) ~[na:na]
	at de.tschuehly.modulitheclipseserializer.RetryMechanism.completePublicationsOlderThan(RetryMechanism.java:45) ~[main/:na]
	at de.tschuehly.modulitheclipseserializer.RetryMechanism.retries(RetryMechanism.java:37) ~[main/:na]
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:580) ~[na:na]
	at org.springframework.scheduling.support.ScheduledMethodRunnable.runInternal(ScheduledMethodRunnable.java:130) ~[spring-context-6.1.10.jar:6.1.10]
	at org.springframework.scheduling.support.ScheduledMethodRunnable.lambda$run$2(ScheduledMethodRunnable.java:124) ~[spring-context-6.1.10.jar:6.1.10]
	at io.micrometer.observation.Observation.observe(Observation.java:499) ~[micrometer-observation-1.13.1.jar:1.13.1]
	at org.springframework.scheduling.support.ScheduledMethodRunnable.run(ScheduledMethodRunnable.java:124) ~[spring-context-6.1.10.jar:6.1.10]
	at org.springframework.scheduling.support.DelegatingErrorHandlingRunnable.run(DelegatingErrorHandlingRunnable.java:54) ~[spring-context-6.1.10.jar:6.1.10]
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:572) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.runAndReset$$$capture(FutureTask.java:358) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.runAndReset(FutureTask.java) ~[na:na]
	at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:305) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:1570) ~[na:na]
```

The RetryMechanism currently creates infinite amounts of events:[RetryMechanism.java](src/main/java/de/tschuehly/modulitheclipseserializer/RetryMechanism.java)
