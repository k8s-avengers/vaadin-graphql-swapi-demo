package net.k8savengers.vaadingraphql;

import net.k8savengers.vaadingraphql.codegen.client.AllFilmsGraphQLQuery;
import net.k8savengers.vaadingraphql.codegen.client.AllFilmsProjectionRoot;
import net.k8savengers.vaadingraphql.codegen.types.Film;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.client.DgsGraphQlClient;

@SpringBootTest
class VaadingraphqlApplicationTests {

    @Autowired
    private DgsGraphQlClient dgsClient;

    @Test
    void contextLoads() {
    }

    @Test
    void testGraphQLClient() {
        var list = dgsClient.request(new AllFilmsGraphQLQuery.Builder().build())
                .projection(new AllFilmsProjectionRoot<>().films().title().director().id())
                .retrieveSync("allFilms.films").toEntityList(Film.class);

        assert !list.isEmpty();

    }

}
