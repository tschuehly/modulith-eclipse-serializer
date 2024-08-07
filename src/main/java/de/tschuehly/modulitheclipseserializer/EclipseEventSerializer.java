package de.tschuehly.modulitheclipseserializer;

import java.util.Base64;
import org.eclipse.serializer.Serializer;
import org.eclipse.serializer.SerializerFoundation;
import org.eclipse.serializer.TypedSerializer;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.Primary;
import org.springframework.modulith.events.core.EventSerializer;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EclipseEventSerializer implements EventSerializer, BeanClassLoaderAware {

  private Serializer<byte[]> serializer;

  @Override
  public void setBeanClassLoader(ClassLoader classLoader) {

    var foundation = SerializerFoundation.New();
    foundation.setClassLoaderProvider(__ -> classLoader);

    serializer = TypedSerializer.Bytes(foundation);
  }

  @Override
  public Object serialize(Object event) {

    var bytes = serializer.serialize(event);

    return Base64.getEncoder().encodeToString(bytes);
  }

  @Override
  public <T> T deserialize(Object serialized, Class<T> type) {
    return serializer.deserialize(Base64.getDecoder().decode((String) serialized));
  }
}