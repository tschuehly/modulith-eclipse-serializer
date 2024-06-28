/*
 * Copyright 2022-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.tschuehly.modulitheclipseserializer;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.modulith.events.config.EventPublicationAutoConfiguration;
import org.springframework.modulith.events.config.EventPublicationConfigurationExtension;
import org.springframework.modulith.events.core.EventSerializer;

/**
 * @author Dmitry Belyaev
 * @author Björn Kieling
 * @author Oliver Drotbohm
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(EventPublicationAutoConfiguration.class)
class JdbcEventPublicationAutoConfiguration implements EventPublicationConfigurationExtension {

  @Bean
  JdbcEventPublicationRepository jdbcEventPublicationRepository(JdbcTemplate jdbcTemplate,
      EventSerializer serializer) {

    return new JdbcEventPublicationRepository(jdbcTemplate, serializer);
  }
}
