package lt.kkava.burbulu_dede.views.simple;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import lt.kkava.burbulu_dede.domain.Constants;
import lt.kkava.burbulu_dede.domain.RenginioUzsakymas;
import lt.kkava.burbulu_dede.services.EmailServiceBurbulai;
import lt.kkava.burbulu_dede.services.RenginioUzsakymasService;
import lt.kkava.burbulu_dede.views.MainLayout;
import lt.kkava.common_utils.ui.services.UtilitiesUI;
import org.apache.commons.lang3.StringUtils;

@PageTitle("Užsakyti šventę")
@Route(value = "order_celebration", layout = MainLayout.class)
public class RenginioUzsakymasView extends VerticalLayout {
    public RenginioUzsakymasView(UtilitiesUI utilitiesUI, RenginioUzsakymasService renginioUzsakymasService, EmailServiceBurbulai emailServiceBurbulai) {
        this.setClassName("home-paragraph");

        VerticalLayout layout = new VerticalLayout();
        layout.getStyle().setAlignSelf(Style.AlignSelf.CENTER);
        if(!isMobile()) layout.setMaxWidth("80%");

        H2 header = new H2("Pakviesk Burbulų Dėdę į savo šventę!");

        TextField renginioTipas = new TextField("Koks tai renginys?", "Vestuvės, gimtadienis, krikštynos, darželio išleistuvės, įmonės šventė ...");
        TextField renginioVieta = new TextField("Vieta", "Kuriame mieste, ar kurioje sodyboje jis vyks?");
        DateTimePicker renginioLaikas = new DateTimePicker("Kokia renginio data ir laikas?");
        renginioLaikas.setLocale(Constants.LOCALE_VILNIUS);
        TextField renginioZmones = new TextField("Kiek bus dalyvaujančių žmonių/vaikų? Kokio maždaug amžiaus vaikai?");
        TextField pasirodymoTrukme = new TextField("Kiek laiko turėtų trukti pasirodymas?");
        TextField email = new TextField("Elektroninis paštas");
        TextField phone = new TextField("Telefonas");
        TextField name = new TextField("Vardas");

        Button send = new Button("Siųsti", e -> {
            renginioTipas.setInvalid(StringUtils.isBlank(renginioTipas.getValue()));
            renginioVieta.setInvalid(StringUtils.isBlank(renginioVieta.getValue()));
            renginioLaikas.setInvalid(renginioLaikas.getValue() == null);
            renginioZmones.setInvalid(StringUtils.isBlank(renginioZmones.getValue()));
            pasirodymoTrukme.setInvalid(StringUtils.isBlank(pasirodymoTrukme.getValue()));
            email.setInvalid(StringUtils.isBlank(email.getValue()));
            phone.setInvalid(StringUtils.isBlank(phone.getValue()));
            name.setInvalid(StringUtils.isBlank(name.getValue()));

            if (!utilitiesUI.isValid(renginioTipas, renginioVieta, renginioLaikas, renginioZmones, pasirodymoTrukme, email, phone, name)) {
                return;
            }
            RenginioUzsakymas renginioUzsakymas = new RenginioUzsakymas()
                    .withName(name.getValue())
                    .withEmail(email.getValue())
                    .withPhone(phone.getValue())
                    .withPasirodymoTrukme(pasirodymoTrukme.getValue())
                    .withRenginioTipas(renginioTipas.getValue())
                    .withRenginioVieta(renginioVieta.getValue())
                    .withRenginioLaikas(renginioLaikas.getValue())
                    .withRenginioZmones(renginioZmones.getValue());
            renginioUzsakymasService.save(renginioUzsakymas);
            emailServiceBurbulai.sendRenginioUzsakymas(renginioUzsakymas);

            Notification.show("Jūsų užklausa sėkmingai išsiųsta Burbulų dėdei. Jis būtinai su Jumis susisieks. Iki pasimatymo!");
            utilitiesUI.clear(renginioTipas, renginioVieta, renginioLaikas, renginioZmones, pasirodymoTrukme, email, phone, name);
        });

        FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
        formLayout.add(renginioTipas, renginioVieta, renginioLaikas, renginioZmones, pasirodymoTrukme, name, email, phone, send);
        layout.add(header, formLayout);
        add(layout);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.START);
        setDefaultHorizontalComponentAlignment(Alignment.START);
    }

    public boolean isMobile() {
        WebBrowser browser = VaadinSession.getCurrent().getBrowser();
        return browser.isAndroid() || browser.isIPhone() || browser.isWindowsPhone();
    }
}
