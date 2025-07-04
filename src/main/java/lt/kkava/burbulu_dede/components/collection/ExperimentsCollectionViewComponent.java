package lt.kkava.burbulu_dede.components.collection;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouteConfiguration;
import lt.kkava.burbulu_dede.domain.Product;
import lt.kkava.burbulu_dede.views.shop.ExperimentsProductView;

public class ExperimentsCollectionViewComponent extends VerticalLayout {

    public ExperimentsCollectionViewComponent(Product product) {
        this.setWidth("320px");
        this.getStyle().set("height", "auto");
        this.setSpacing(false);
        this.setJustifyContentMode(JustifyContentMode.CENTER);
        this.setAlignItems(Alignment.CENTER);
        this.setClassName("home-paragraph");
        this.setId("collection-layout");

        String route = RouteConfiguration.forSessionScope().getUrl(ExperimentsProductView.class, product.getItemId());
        Anchor mainAnchor = new Anchor(route);
        mainAnchor.getStyle().set("text-decoration", "none");

        Image image = new Image();
        image.setId("product-image");
        image.setSrc(product.getImgSrc());
        image.setWidth("300px");
        image.setHeight("300px");

        H4 title = new H4();
        title.setId("product-title");
        title.getStyle().set("padding", "10px");
        title.setText(product.getItemTitle());

        H4 price = new H4();
        price.setId("product-price");
        if (product.getItems().size() == 1) {
            price.setText(product.getItems().getFirst().getItemPrice() + "€");
        } else {
            price.setText(product.getItems().getFirst().getItemPrice() + " - " + product.getItems().getLast().getItemPrice() + "€");
        }


        mainAnchor.add(image, title, price);
        add(mainAnchor);
    }
}
