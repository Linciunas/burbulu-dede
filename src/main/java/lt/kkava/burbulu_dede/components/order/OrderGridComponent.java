package lt.kkava.burbulu_dede.components.order;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import lt.kkava.burbulu_dede.domain.ProductItem;
import lt.kkava.burbulu_dede.domain.ProduktuUzsakymas;

import java.util.List;

public class OrderGridComponent extends VerticalLayout {
    private int amount;
    private int sum;

    private H4 sumContainer = new H4();
    Grid<ProductItem> grid = new Grid<>(ProductItem.class, false);
    public OrderGridComponent(ProduktuUzsakymas produktuUzsakymas) {
        this.getStyle().setPadding("0px");
        this.setAlignItems(Alignment.CENTER);
        this.getStyle().setPadding("0px");
        this.getStyle().set("width", "auto");
        grid.getStyle().set("margin-left", "auto");
        grid.getStyle().set("margin-right", "auto");

        List<ProductItem> productItems = produktuUzsakymas.getProductItems();

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
        grid.setAllRowsVisible(true);

        grid.setItems(productItems);

        grid.addColumn(new ComponentRenderer<>((productItem) -> {
            HorizontalLayout horizontalLayout = new HorizontalLayout();

            Button deleteButton = new Button(new Icon(VaadinIcon.CLOSE_SMALL));
            deleteButton.addClickListener(buttonClickEvent -> {
                productItems.remove(productItem);
                produktuUzsakymas.setProductItems(productItems);
                grid.getDataProvider().refreshAll();
                sum -= productItem.getItemPrice() * productItem.getAmount();
                sumContainer.setText(sum + "€");
                if (productItems.isEmpty()) {
                    this.removeAll();
                    add(new EmptyShoppingCartComponent());
                    this.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
                }
            });

            horizontalLayout.add(deleteButton);
            return horizontalLayout;
        })).setAutoWidth(true).setFlexGrow(0);

        grid.addColumn(createImage()).setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(ProductItem::getItemTitle).setHeader("PRODUKTAS").setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(ProductItem::getInformation).setHeader("").setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(createPrice()).setHeader("KAINA").setAutoWidth(true).setFlexGrow(1);
        grid.addColumn(new ComponentRenderer<>(productItem -> {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
            horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

            Span amountSpan = new Span(String.valueOf(productItem.getAmount()));

            Button minusButton = new Button(new Icon(VaadinIcon.MINUS));
            minusButton.setWidth("40px");
            minusButton.addClickListener(buttonClickEvent -> {
                if (productItem.getAmount() != 1) {
                    amount = productItem.getAmount();
                    amount--;
                    productItem.setAmount(amount);
                    sum -= productItem.getItemPrice();
                    amountSpan.setText(String.valueOf(amount));
                    sumContainer.setText(String.valueOf(sum) + "€");
                    grid.setItems(productItems);
                }
            });

            Button plusButton = new Button(new Icon(VaadinIcon.PLUS));
            plusButton.setWidth("40px");
            plusButton.addClickListener(buttonClickEvent -> {
                amount = productItem.getAmount();
                amount++;
                sum += productItem.getItemPrice();
                productItem.setAmount(amount);
                amountSpan.setText(String.valueOf(amount));
                sumContainer.setText(sum + "€");
                grid.setItems(productItems);
            });

            horizontalLayout.add(minusButton, amountSpan, plusButton);
            return horizontalLayout;
        })).setHeader("KIEKIS").setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(createTotalPrice()).setHeader("SUMA").setAutoWidth(true).setFlexGrow(0);




        HorizontalLayout sumWrapper = new HorizontalLayout();
        sumWrapper.setWidthFull();
        sumWrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        sumWrapper.getStyle().set("gap", "5px");
        sumWrapper.getStyle().set("padding-left", "20px");
        sumWrapper.getStyle().set("padding-right", "20px");

        sumWrapper.add(new H4("Suma:"));

        for (ProductItem productItem : productItems) {
            sum += productItem.getItemPrice() * productItem.getAmount();
        }

        sumContainer.setText(sum + "€");
        sumWrapper.add(sumContainer);

        add(grid, sumWrapper);

    }

    public static Renderer<ProductItem> createImage() {
        return LitRenderer.<ProductItem>of(
                        "<vaadin-avatar img=\"${item.pictureUrl}\" </vaadin-avatar>")
                .withProperty("pictureUrl", ProductItem::getImageSrc);
    }

    public static Renderer<ProductItem> createPrice() {
        return LitRenderer.<ProductItem>of(
                        "<span> ${item.price}€ </span>")
                .withProperty("price", ProductItem::getItemPrice);
    }

    public static Renderer<ProductItem> createTotalPrice() {
        return LitRenderer.<ProductItem>of(
                        "<span> ${item.price*item.amount}€ </span>")
                .withProperty("price", ProductItem::getItemPrice).withProperty("amount", ProductItem::getAmount);
    }
}
