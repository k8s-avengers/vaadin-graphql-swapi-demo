package net.k8savengers.vaadingraphql;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.vaadin.lineawesome.LineAwesomeIcon;

//@Menu(icon = "line-awesome/svg/pencil-ruler-solid.svg", order = 0)
@PageTitle("Index")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class IndexView extends Composite<VerticalLayout> {

    public IndexView() {
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        // Add a Vaadin button that uses a Vaadin icon
        Button someButton = new Button("Click me");
        someButton.setIcon(LineAwesomeIcon.PENCIL_RULER_SOLID.create());
        getContent().add(someButton);
    }
}
