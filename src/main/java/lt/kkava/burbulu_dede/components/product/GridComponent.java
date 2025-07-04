package lt.kkava.burbulu_dede.components.product;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataChangeEvent;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import lt.kkava.burbulu_dede.domain.Product;
import lt.kkava.burbulu_dede.domain.ProductItem;

import java.util.List;

public class GridComponent extends Div {
    int amount;
    Product product;
    private final Grid<ProductItem> grid = new Grid<>(ProductItem.class, false);

    public GridComponent(Product product) {
        this.product = product;
        //this.setWidth("auto");
        List<ProductItem> items = product.getItems();

        grid.setItems(items);
        grid.addColumn(ProductItem::getInformation).setAutoWidth(true).setWidth("125px").setFlexGrow(0);
        grid.addColumn(createPrice()).setHeader("KAINA").setAutoWidth(true).setWidth("125px");
        grid.addColumn(new ComponentRenderer<>(item -> {

            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
            horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

            Span amountSpan = new Span(String.valueOf(item.getAmount()));

            Button minusButton = new Button(new Icon(VaadinIcon.MINUS));
            minusButton.setWidth("40px");
            minusButton.addClickListener(buttonClickEvent -> {
                if (item.getAmount() != 0) {
                    item.setAmount(item.getAmount() - 1);
                    amountSpan.setText(String.valueOf(item.getAmount()));
                    grid.getDataProvider().refreshItem(item);
                }
            });

            Button plusButton = new Button(new Icon(VaadinIcon.PLUS));
            plusButton.setWidth("40px");
            plusButton.addClickListener(buttonClickEvent -> {
                amount = item.getAmount();
                amount++;
                item.setAmount(amount);
                amountSpan.setText(String.valueOf(item.getAmount()));
                grid.getDataProvider().refreshItem(item);
            });

            horizontalLayout.add(minusButton, amountSpan, plusButton);
            return horizontalLayout;
        })).setHeader("KIEKIS").setAutoWidth(true).setWidth("175px");
        styleGrid();
        add(grid);
    }

    public static LitRenderer<ProductItem> createPrice() {
        return LitRenderer.<ProductItem>of(
                        "<span> ${item.price}€ </span>")
                .withProperty("price", ProductItem::getItemPrice);
    }

    public void styleGrid() {
        grid.addThemeVariants(GridVariant.LUMO_COMPACT);
        grid.setMinWidth("300px");
        grid.getStyle().setTextAlign(Style.TextAlign.CENTER);
        grid.getStyle().setMarginTop("15px");
        grid.getStyle().setMarginBottom("15px");

        if(product.getItems().size() > 4) {
            grid.getStyle().setMaxHeight("301px");
        } else {
            grid.setAllRowsVisible(true);
        }
        //if(isMobile()) grid.getStyle().set("scale","0.75");
    }

    public boolean isMobile() {
        WebBrowser browser = VaadinSession.getCurrent().getBrowser();
        return browser.isAndroid() || browser.isIPhone() || browser.isWindowsPhone();
    }
}
