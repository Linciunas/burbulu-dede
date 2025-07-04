package lt.kkava.burbulu_dede.components.order;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.theme.lumo.LumoUtility;
import lt.kkava.burbulu_dede.components.events.OrderChangeEvent;
import lt.kkava.burbulu_dede.domain.ProductItem;
import lt.kkava.burbulu_dede.domain.ProduktuUzsakymas;

import java.util.List;

public class PhoneOrderViewComponent extends HorizontalLayout {
    private int amount = 1;
    private int sum;
    private Span totalSpan = new Span();

    public PhoneOrderViewComponent(ProductItem productItem, ProduktuUzsakymas produktuUzsakymas) {
        setWidthFull();
        setId("order");
        getStyle().set("gap", "10px");
        sum = productItem.getItemPrice() * productItem.getAmount();
        setAlignItems(Alignment.CENTER);
        getThemeList().add(LumoUtility.Gap.SMALL);

        Image image = new Image();
        image.setId("order-image");

        image.setSrc(productItem.getImageSrc());

        VerticalLayout description = new VerticalLayout();
        description.getStyle().set("padding-left", "0px");
        description.getStyle().set("padding-right", "0px");

        HorizontalLayout titleWrapper = new HorizontalLayout();
        titleWrapper.setId("order-title");
        titleWrapper.setWidthFull();
        titleWrapper.setAlignItems(Alignment.CENTER);
        titleWrapper.setJustifyContentMode(JustifyContentMode.BETWEEN);
        H4 title = new H4(productItem.getItemTitle());

        Button delete = new Button(new Icon(VaadinIcon.CLOSE_SMALL));
        delete.setId("order-delete");
        delete.addClickListener(buttonClickEvent -> {
            List<ProductItem> products = produktuUzsakymas.getProductItems();
            products.remove(productItem);
            produktuUzsakymas.setProductItems(products);
            this.removeAll();
            getStyle().set("visibility", "hidden");
            fireEvent(new OrderChangeEvent(this, true, false, false, true, productItem));
        });

        titleWrapper.add(title, delete);

        HorizontalLayout priceWrapper = new HorizontalLayout();
        priceWrapper.setId("order-price");
        priceWrapper.setWidthFull();
        priceWrapper.setAlignItems(Alignment.CENTER);
        priceWrapper.setJustifyContentMode(JustifyContentMode.BETWEEN);

        H5 priceTitle = new H5("KAINA");
        Span price = new Span(productItem.getItemPrice() + "€");
        priceWrapper.add(priceTitle, price);

        HorizontalLayout amountWrapper = new HorizontalLayout();
        amountWrapper.setId("order-amount");
        amountWrapper.setWidthFull();
        amountWrapper.setAlignItems(Alignment.CENTER);
        amountWrapper.setJustifyContentMode(JustifyContentMode.BETWEEN);

        H5 amountTitle = new H5("KIEKIS");
        Span amountSpan = new Span(String.valueOf(productItem.getAmount()));

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.getStyle().set("padding-bottom", "17px");
        buttonLayout.setHeight("40px");
        buttonLayout.setAlignItems(Alignment.CENTER);

        Button minusButton = new Button("-");
        minusButton.setId("product-minus-button");
        minusButton.setMinWidth("40px");
        minusButton.addClickListener(buttonClickEvent -> {
            if (amount != 1) {
                amount -= 1;
                amountSpan.setText(String.valueOf(amount));
                sum -= productItem.getItemPrice();
                totalSpan.setText(sum + "€");
                productItem.setAmount(amount);
                fireEvent(new OrderChangeEvent(this, true, false, true, false, productItem));
            }
        });

        Button plusButton = new Button("+");
        plusButton.setId("product-plus-button");
        plusButton.setMinWidth("40px");
        plusButton.addClickListener(buttonClickEvent -> {
            amount += 1;
            amountSpan.setText(String.valueOf(amount));
            sum += productItem.getItemPrice();
            totalSpan.setText(sum + "€");
            productItem.setAmount(amount);
            fireEvent(new OrderChangeEvent(this, true, true, false, false, productItem));
        });
        buttonLayout.add(minusButton, amountSpan, plusButton);
        amountWrapper.add(amountTitle, buttonLayout);

        HorizontalLayout totalWrapper = new HorizontalLayout();
        totalWrapper.setId("order-total-price");
        totalWrapper.setWidthFull();
        totalWrapper.setAlignItems(Alignment.CENTER);
        totalWrapper.setJustifyContentMode(JustifyContentMode.BETWEEN);

        HorizontalLayout infoWrapper = new HorizontalLayout();
        infoWrapper.setId("order-total-price");
        infoWrapper.setWidthFull();
        infoWrapper.setAlignItems(Alignment.CENTER);
        infoWrapper.setJustifyContentMode(JustifyContentMode.BETWEEN);
        H5 info = new H5("INFO");
        Span infoSpan = new Span(String.valueOf(productItem.getInformation()));
        infoWrapper.add(info, infoSpan);

        description.add(titleWrapper, priceWrapper, amountWrapper);

        if(!productItem.getInformation().isEmpty()) description.add(infoWrapper);

        H5 totalTitle = new H5("SUMA");
        totalSpan.setText(sum + "€");
        totalWrapper.add(totalTitle, totalSpan);

        description.add(totalWrapper);

        add(image, description);
    }

    public Registration addChangeListener(ComponentEventListener<OrderChangeEvent> listener) {
        return addListener(OrderChangeEvent.class, listener);
    }
}
