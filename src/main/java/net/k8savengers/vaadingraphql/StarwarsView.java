package net.k8savengers.vaadingraphql;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import net.k8savengers.vaadingraphql.codegen.client.AllFilmsGraphQLQuery;
import net.k8savengers.vaadingraphql.codegen.client.AllFilmsProjectionRoot;
import net.k8savengers.vaadingraphql.codegen.types.Film;
import org.springframework.graphql.client.DgsGraphQlClient;

@PageTitle("Star Wars")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class StarwarsView extends Composite<VerticalLayout> {

    public StarwarsView(DgsGraphQlClient dgsClient) {
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        Grid<Film> grid = new Grid<>(Film.class, true);

        Button getDataButton = new Button("Get Star Wars Data");
        getDataButton.addClickListener(event -> {
            // query graphql asynchronously, when data arrives set the grid items
            dgsClient.request(new AllFilmsGraphQLQuery.Builder().build())
                    .projection(new AllFilmsProjectionRoot<>().films().title().director().id().openingCrawl())
                    .retrieve("allFilms.films")
                    .toEntityList(Film.class)
                    .subscribe(items -> {
                        getUI().ifPresent(ui -> ui.access(() -> grid.setItems(items)));
                    });
        });
        getContent().add(getDataButton);
        getContent().add(grid);
    }
}
