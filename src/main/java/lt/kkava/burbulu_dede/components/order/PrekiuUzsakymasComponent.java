package lt.kkava.burbulu_dede.components.order;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import lt.kkava.burbulu_dede.domain.Constants;
import lt.kkava.burbulu_dede.domain.ProduktuUzsakymas;
import lt.kkava.burbulu_dede.services.EmailServiceBurbulai;
import lt.kkava.burbulu_dede.services.OrderService;
import lt.kkava.common_utils.ui.services.UtilitiesUI;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class PrekiuUzsakymasComponent extends VerticalLayout {
    private final TextField name = new TextField("Vardas");
    private final TextField email = new TextField("Elektroninis paštas");
    private final TextField phone = new TextField("Telefonas");
    private final ConfirmDialog dialog = new ConfirmDialog();
    private final TextField delivery = new TextField("Nurodykite miestą į kurį išsiųsti");
    private final RadioButtonGroup<String> radioButtonGroup = new RadioButtonGroup<>();
    public PrekiuUzsakymasComponent(ProduktuUzsakymas produktuUzsakymas, UtilitiesUI utilitiesUI, OrderService orderService,
                                    EmailServiceBurbulai emailServiceBurbulai) {
        this.setJustifyContentMode(JustifyContentMode.START);
        this.setHeightFull();
        this.getStyle().set("width", "auto");
        this.getStyle().set("margin", "auto");
        this.getStyle().set("gap", "0px");
        this.setAlignItems(Alignment.CENTER);

        OrderGridComponent grid = new OrderGridComponent(produktuUzsakymas);
        grid.addClickListener(event -> {
            if(produktuUzsakymas.getProductItems().isEmpty()){
                this.removeAll();
                this.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
                add(new EmptyShoppingCartComponent());
            }
        });
        add(grid);

        HorizontalLayout emailWrapper = new HorizontalLayout();
        emailWrapper.setWidthFull();
        name.setWidth("33%");
        email.setWidth("34%");
        phone.setWidth("33%");
        emailWrapper.add(name, email, phone);

        HorizontalLayout addressWrapper = new HorizontalLayout();
        addressWrapper.setWidthFull();
        radioButtonGroup.setLabel("Kaip norėtumėte gauti siuntinuką?");
        radioButtonGroup.setItems("Skubiai (jau rytoj) per „Siuntos autobusais“","Neskubiai (per 3 darbo dienas) paštomatu.");
        radioButtonGroup.setValue("Skubiai (jau rytoj) per „Siuntos autobusais“");
        radioButtonGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);

        VerticalLayout radioButtonLayout = new VerticalLayout();
        radioButtonLayout.add(delivery);
        radioButtonLayout.setWidth("auto");
        radioButtonLayout.getStyle().setPadding("0px");
        radioButtonLayout.setWidth("400px");
        delivery.setWidth("400px");

        radioButtonGroup.addValueChangeListener(event -> {
            String value = event.getValue();
            radioButtonLayout.removeAll();
            if(value.equals("Skubiai (jau rytoj) per „Siuntos autobusais“")) {
                delivery.setLabel("Nurodykite miestą į kurį išsiųsti");
            } else {
                delivery.setLabel("Nurodykite miestą ir kokio prekybcentrio paštomatą išsiųsti");
            }
            radioButtonLayout.add(delivery);
            addressWrapper.add(radioButtonLayout);
        });

        addressWrapper.add(radioButtonGroup, radioButtonLayout);
        dialog.setHeader("Reikia patvirtinimo");
        dialog.setText("Tikrai daugiau nieko nereikia?");
        dialog.setCancelable(true);
        dialog.setCancelText("Ne, dar pažiūrinėsiu");
        dialog.setConfirmText("Taip, jau viskas");

        dialog.addConfirmListener(event -> {
            email.setInvalid(StringUtils.isBlank(email.getValue()));
            phone.setInvalid(StringUtils.isBlank(phone.getValue()));
            name.setInvalid(StringUtils.isBlank(name.getValue()));
            delivery.setInvalid(StringUtils.isBlank(delivery.getValue()));

            if (!utilitiesUI.isValid(name, email, phone, delivery)) {
                return;
            }

            produktuUzsakymas.setEmail(email.getValue());
            produktuUzsakymas.setPhone(phone.getValue());
            produktuUzsakymas.setName(name.getValue());
            produktuUzsakymas.setAddress(delivery.getValue());
            produktuUzsakymas.setMode(radioButtonGroup.getValue());
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
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

        });

        Button send = new Button("Užsakyti", e -> {
            dialog.open();
        });
        add(emailWrapper, addressWrapper, send);
    }
}
