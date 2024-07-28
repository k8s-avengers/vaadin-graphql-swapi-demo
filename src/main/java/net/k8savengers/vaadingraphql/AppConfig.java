package net.k8savengers.vaadingraphql;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;

/**
 * Most of this will be configured via Env vars. See <a href="https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#features.external-config.typesafe-configuration-properties.relaxed-binding.environment-variables">Spring docs</a>
 */
@ConfigurationProperties(prefix = "app")
@Data
@NoArgsConstructor
public class AppConfig {
    private URI swapi_graphql_endpoint;
}
