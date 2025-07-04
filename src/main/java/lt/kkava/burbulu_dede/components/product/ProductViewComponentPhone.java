package lt.kkava.burbulu_dede.components.product;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import lt.kkava.burbulu_dede.domain.Constants;
import lt.kkava.burbulu_dede.domain.ProductItem;
import lt.kkava.burbulu_dede.domain.ProduktuUzsakymas;
import lt.kkava.burbulu_dede.domain.Product;
import lt.kkava.burbulu_dede.services.BasketService;

import java.util.List;
import java.util.Objects;

@PageTitle("PARDUOTUVĖ")

public class ProductViewComponentPhone extends VerticalLayout {
    private final Product product;
    private final BasketService basketService;
    public ProductViewComponentPhone(Product product, BasketService basketService, String route) {
        this.product = product;
        this.basketService = basketService;
        this.setAlignItems(Alignment.CENTER);
        this.setAlignSelf(Alignment.CENTER);
        this.getStyle().set("padding-top", "0px");
        this.getStyle().set("gap","0px");
        this.getStyle().setMargin("auto");
        HorizontalLayout goBack = new HorizontalLayout();
        goBack.setAlignItems(Alignment.CENTER);
        goBack.setWidthFull();

        Icon arrow = new Icon(VaadinIcon.ARROW_BACKWARD);
        H4 back = new H4("Grįžti");

        Anchor link = new Anchor(route);
        link.getStyle().set("text-decoration", "none");

        goBack.add(arrow, back);
        link.add(goBack);

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.getStyle().setPaddingBottom("0px");
        mainLayout.setId("product-layout");

        Image image = new Image();
        image.setSrc(product.getImgSrc());
        image.setHeight("450px");
        image.setId("product-image");

        VerticalLayout sideLayout = new VerticalLayout();
        sideLayout.setId("product-description-layout");
        sideLayout.setAlignItems(Alignment.CENTER);
        sideLayout.setSpacing(false);

        H3 title = new H3(product.getItemTitle());

        GridComponent grid = new GridComponent(product);
        add(grid);

        HorizontalLayout endWrapper = new HorizontalLayout();
        endWrapper.setAlignItems(Alignment.CENTER);
        endWrapper.setAlignSelf(Alignment.CENTER);
        Button addToBasket = new Button("Pridėti į krepšelį");
        endWrapper.add(addToBasket);
        if(product.getInstructions().length > 0) {
            Anchor anchor = new Anchor(getInstructionSrc(product), "Instrukcija");
            anchor.getStyle().set("text-decoration", "none");
            anchor.getElement().setAttribute("router-ignore", true);
            anchor.setTarget("_blank");
            endWrapper.add(anchor);
        }

        addToBasket.addClickListener(buttonClickEvent -> {
            createOrder();
        });

        sideLayout.add(title);

        sideLayout.add(grid, endWrapper);
        mainLayout.add(image, sideLayout);
        add(link, mainLayout);

        TabComponent tabs = new TabComponent(product);
        if(!Objects.equals(product.getItemId(), "nuoma")) add(tabs);

    }
    public String getInstructionSrc(Product product) {
        return switch (product.getItemId()) {
            case "burbulu_milteliai" -> "/assets/burbulu_milteliai_instrukcija.pdf";
            case "kirmeles" -> "/assets/kirmeliu_instrukcija.pdf";
            case "drebuciai" -> "/assets/drebuciu_instrukcija.pdf";
            case "pampersinis_sniegas" -> "/assets/pampersinis_sniegas.pdf";
            case "dregnas_sniegas" -> "/assets/dregnas_sniegas_instrukcija.pdf";
            case "sausas_sniegas" -> "/assets/sausas_sniegas_instrukcija.pdf";
            case "orbeez" -> "/assets/orbeez_instrukcija.pdf";
            case "burbulu_milteliai_masinoms" -> "/assets/masinu_instrukcija.pdf";
            default -> "";
        };
    }

    public void createOrder() {
        ProduktuUzsakymas produktuUzsakymas = (ProduktuUzsakymas) VaadinSession.getCurrent().getSession().getAttribute(Constants.SESSION_ORDER);
        if (produktuUzsakymas == null) {
            produktuUzsakymas = new ProduktuUzsakymas();
            VaadinSession.getCurrent().getSession().setAttribute(Constants.SESSION_ORDER, produktuUzsakymas);
        }
        basketService.updateBasket(produktuUzsakymas, product);
        Notification notification = Notification.show("Prekė įdėta į krepšelį");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }

}
