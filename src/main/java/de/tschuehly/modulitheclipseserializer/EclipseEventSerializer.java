package de.tschuehly.modulitheclipseserializer;

import org.eclipse.serializer.Serializer;
import org.eclipse.serializer.SerializerFoundation;
import org.eclipse.serializer.TypedSerializer;
import org.springframework.modulith.events.core.EventSerializer;
import org.springframework.stereotype.Component;

@Component
public class EclipseEventSerializer implements EventSerializer {

  private final Serializer<byte[]> serializer;

  public EclipseEventSerializer() {
    SerializerFoundation<?> foundation = SerializerFoundation.New();
    serializer = TypedSerializer.Bytes(foundation);
  }

  @Override
  public Object serialize(Object event) {
    return serializer.serialize(event);
  }

  @Override
  public <T> T deserialize(Object serialized, Class<T> type) {
    return serializer.deserialize((byte[]) serialized);
  }
}
