package lt.kkava.burbulu_dede.views.simple;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinServlet;
import lt.kkava.burbulu_dede.views.MainLayout;

import java.util.Objects;

@PageTitle("Kontaktai")
@Route(value = "contacts", layout = MainLayout.class)
public class ContactsView extends VerticalLayout {
    public ContactsView() {
        setSpacing(false);
        setAlignItems(Alignment.CENTER);
        this.addClassNames("home-paragraph");
        createLayout("Telefonas", "+370 672 27185", "tel:+37067227185");
        createLayout("Elektroninis paštas", "burbuludede@gmail.com", "mailto:burbuludede@gmail.com");
        createLayout("Atsiskaitomoji sąskaita", "ARNAS PUPLAUSKAS, LT393500010002660526 (Banko pavadinimas: „Paysera LT“, UAB. Banko kodas: 35000)","");

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.getStyle().set("gap", "5px");
        horizontalLayout.setAlignItems(Alignment.CENTER);

        Anchor anchor = new Anchor("assets/veiklos_pazyma.pdf", "INDIVIDUALIOS VEIKLOS PAŽYMA");
        anchor.getElement().setAttribute("router-ignore", true);
        anchor.setTarget("_blank");
        anchor.getStyle().set("text-decoration", "none");
        anchor.getStyle().set("margin-bottom", "2px");

        H4 anchorTitle = new H4("Veikla vykdoma pagal individualios veiklos pažymą. Išrašau sąskaitas-faktūras.");
        anchorTitle.getStyle().set("text-align", "center");

        horizontalLayout.add(anchorTitle);

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.getStyle().set("padding", "36px");
        verticalLayout.setAlignItems(Alignment.CENTER);
        H3 title = new H3("Draugaukime Facebook'e");
        title.getStyle().set("text-align", "center");

        verticalLayout.add(title);
        Anchor anchorFb = new Anchor("https://www.facebook.com/profile.php?id=100008281451294");
        anchorFb.setTarget("_blank");
        anchorFb.add(new Icon(VaadinIcon.FACEBOOK_SQUARE));
        verticalLayout.add(anchorFb);

        Anchor anchorKids = new Anchor("assets/teiseto_darbo_su_vaikais_kodas.pdf", "TEISĖTO DARBO SU VAIKAIS KODAS");
        anchorKids.setTarget("_blank");
        add(horizontalLayout, anchor, verticalLayout, anchorKids);
    }

    public void createLayout(String title, String paragraph, String href) {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setAlignItems(Alignment.CENTER);
        H3 titleWrapper = new H3(title);

        Paragraph p = new Paragraph(paragraph);
        p.getStyle().set("margin", "0px");
        p.getStyle().set("text-align", "center");

        if (Objects.equals(href, "")) {
            verticalLayout.add(titleWrapper, p);
        } else {
            Anchor a = new Anchor(href);
            a.getStyle().set("text-decoration", "none");
            a.add(p);
            verticalLayout.add(titleWrapper, a);
        }

        add(verticalLayout);
    }
}
