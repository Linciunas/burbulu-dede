package lt.kkava.burbulu_dede.views.simple;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import lt.kkava.burbulu_dede.components.order.EmptyShoppingCartComponent;
import lt.kkava.burbulu_dede.components.order.PhoneOrderViewComponent;
import lt.kkava.burbulu_dede.components.events.OrderChangeEvent;
import lt.kkava.burbulu_dede.domain.Constants;
import lt.kkava.burbulu_dede.domain.ProductItem;
import lt.kkava.burbulu_dede.domain.ProduktuUzsakymas;
import lt.kkava.burbulu_dede.services.EmailServiceBurbulai;
import lt.kkava.burbulu_dede.services.OrderService;
import lt.kkava.common_utils.ui.services.UtilitiesUI;
import org.apache.commons.lang3.StringUtils;

public class PhoneOrderView extends VerticalLayout {
    private final TextField name = new TextField("Vardas");

    private final TextField email = new TextField("Elektroninis paštas");
    private final TextField phone = new TextField("Telefonas");
    private int sum = 0;
    H3 price = new H3();

    private final ConfirmDialog dialog = new ConfirmDialog();
    private final TextField delivery = new TextField("Nurodykite miestą į kurį išsiųsti");
    private final RadioButtonGroup<String> radioButtonGroup = new RadioButtonGroup<>();
    public PhoneOrderView(ProduktuUzsakymas produktuUzsakymas, UtilitiesUI utilitiesUI, OrderService orderService, EmailServiceBurbulai emailServiceBurbulai) {
        setSpacing(false);
        getStyle().set("text-align", "center");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        sum = createLayout(produktuUzsakymas);
        HorizontalLayout totalPriceWrapper = new HorizontalLayout();
        totalPriceWrapper.setAlignItems(Alignment.CENTER);
        totalPriceWrapper.setId("order-sum");
        H3 totalPriceTitle = new H3("SUMA");
        price.setText(sum + "€");
        totalPriceWrapper.add(totalPriceTitle, price);
        add(totalPriceWrapper);

        VerticalLayout textFieldWrapper = new VerticalLayout();
        textFieldWrapper.getStyle().set("padding", "0px");
        textFieldWrapper.getStyle().set("gap", "5px");
        textFieldWrapper.setAlignItems(Alignment.CENTER);

        email.setWidthFull();
        phone.setWidthFull();
        name.setWidthFull();
        VerticalLayout radioButtonLayout = new VerticalLayout();
        radioButtonLayout.add(delivery);
        radioButtonLayout.setWidth("auto");
        radioButtonLayout.getStyle().setPadding("0px");

        VerticalLayout addressWrapper = new VerticalLayout();
        addressWrapper.setAlignItems(Alignment.CENTER);
        addressWrapper.getStyle().set("gap", "0px");
        addressWrapper.getStyle().setPadding("0px");
        addressWrapper.setWidthFull();
        radioButtonGroup.setLabel("Kaip norėtumėte gauti siuntinuką?");
        radioButtonGroup.setItems("Skubiai (jau rytoj) per „Siuntos autobusais“","Neskubiai (per 3 darbo dienas) paštomatu.");
        radioButtonGroup.setValue("Skubiai (jau rytoj) per „Siuntos autobusais“");
        radioButtonGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);

        delivery.setWidthFull();

        radioButtonLayout.setWidthFull();

        radioButtonGroup.addValueChangeListener(event -> {
            String value = event.getValue();
            if(value.equals("Skubiai (jau rytoj) per „Siuntos autobusais“")) {
                delivery.setLabel("Nurodykite miestą į kurį išsiųsti");
                delivery.getStyle().set("--vaadin-input-field-label-font-size","14px");
            } else {
                delivery.setLabel("Nurodykite miestą ir kokio prekybcentrio paštomatą išsiųsti");
                delivery.getStyle().set("--vaadin-input-field-label-font-size","10px");
            }
            addressWrapper.add(delivery);
        });

        addressWrapper.add(radioButtonGroup, radioButtonLayout);
        dialog.setHeader("Reikia patvirtinimo");
        dialog.setText("Tikrai daugiau nieko nereikia?");
        dialog.setCancelable(true);
        dialog.setCancelText("Ne, dar pažiūrinėsiu");
        dialog.setConfirmText("Taip, jau viskas");

        dialog.addConfirmListener(event -> {
            name.setInvalid(StringUtils.isBlank(name.getValue()));
            email.setInvalid(StringUtils.isBlank(email.getValue()));
            phone.setInvalid(StringUtils.isBlank(phone.getValue()));
            delivery.setInvalid(StringUtils.isBlank(delivery.getValue()));

            if (!utilitiesUI.isValid(name, email, phone, delivery)) {
                return;
            }
            produktuUzsakymas.setName(name.getValue());
            produktuUzsakymas.setEmail(email.getValue());
            produktuUzsakymas.setPhone(phone.getValue());
            produktuUzsakymas.setMode(radioButtonGroup.getValue());
            produktuUzsakymas.setAddress(delivery.getValue());
            orderService.save(produktuUzsakymas);
            emailServiceBurbulai.sendOrder(produktuUzsakymas);

            Notification notification = Notification.show("Užsakymas sėkmingai užregistruotas, dabar beliko tik palaukti Burbulų dėdės skambučio.");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            this.removeAll();
            this.setJustifyContentMode(JustifyContentMode.CENTER);
            add(new EmptyShoppingCartComponent());

            ProduktuUzsakymas newProduktuUzsakymas = new ProduktuUzsakymas();
            VaadinSession.getCurrent().getSession().setAttribute(Constants.SESSION_ORDER, newProduktuUzsakymas);
        });

        dialog.addCancelListener(event -> {
            Notification notification = Notification.show("Užsakymas atmestas");
            notification.setDuration(2000);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        });

        Button send = new Button("Užsakyti", e -> {
            dialog.open();
        });

        textFieldWrapper.add(name, email, phone, addressWrapper, send);
        add(textFieldWrapper);
    }

    private void handleOrderEvent(OrderChangeEvent event) {
        if (event.isPlus()) {
            sum += event.getObjectProductItem().getItemPrice();
            price.setText(sum + "€");
        }

        if (event.isMinus()) {
            sum -= event.getObjectProductItem().getItemPrice();
            price.setText(sum + "€");
        }

        if (event.isDelete()) {
            sum -= event.getObjectProductItem().getItemPrice() * event.getObjectProductItem().getAmount();
            if (sum == 0) {
                this.removeAll();
                add(new EmptyShoppingCartComponent());
                this.setJustifyContentMode(JustifyContentMode.CENTER);
            } else {
                price.setText(sum + "€");
            }
        }
    }

    private int createLayout(ProduktuUzsakymas produktuUzsakymas) {
        VerticalLayout orderLayout = new VerticalLayout();
        orderLayout.getStyle().set("gap", "10px");
        orderLayout.setId("order-layout");
        orderLayout.getStyle().set("padding", "0px");
        for (ProductItem productItem : produktuUzsakymas.getProductItems()) {
            PhoneOrderViewComponent phoneOrderViewComponent = new PhoneOrderViewComponent(productItem, produktuUzsakymas);
            phoneOrderViewComponent.addChangeListener(this::handleOrderEvent);
            orderLayout.add(phoneOrderViewComponent);
            sum += productItem.getItemPrice() * productItem.getAmount();
        }
        add(orderLayout);
        return sum;
    }
}
