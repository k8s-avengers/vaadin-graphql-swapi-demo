package net.k8savengers.vaadingraphql;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import io.netty.handler.logging.LogLevel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.client.DgsGraphQlClient;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

@SpringBootApplication
@Theme(variant = Lumo.DARK)
@Push
@EnableConfigurationProperties({AppConfig.class})
public class VaadingraphqlApplication implements AppShellConfigurator {


    public static void main(String[] args) {
        SpringApplication.run(VaadingraphqlApplication.class, args);
    }

    @Bean
    public DgsGraphQlClient graphQlClient(AppConfig config) {
        HttpGraphQlClient client = HttpGraphQlClient.builder()
                .url(config.getSwapi_graphql_endpoint())
                .webClient(builder -> {
                    builder.clientConnector(new ReactorClientHttpConnector(
                            HttpClient.create().wiretap("webClientLogger", LogLevel.INFO, AdvancedByteBufFormat.TEXTUAL)
                    ));
                })
                .build();
        return DgsGraphQlClient.create(client);
    }

}
