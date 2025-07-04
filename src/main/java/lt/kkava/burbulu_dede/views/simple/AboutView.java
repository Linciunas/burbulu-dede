package lt.kkava.burbulu_dede.views.simple;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("Apie burbulus")
@Route(value = "about", layout = MainLayout.class)
public class AboutView extends HorizontalLayout {

    public AboutView() {
        this.setClassName("home-paragraph");
        setSpacing(false);
        setJustifyContentMode(JustifyContentMode.CENTER);
        VerticalLayout verticalLayout = new VerticalLayout();
        Paragraph p = new Paragraph("Leidžiant burbulus lazdelėmis, labai svarbu žinoti, kokios sąlygos burbulams patinka, o kokių jie labai nemėgsta.");
        p.getStyle().set("text-align", "center");
        verticalLayout.add(p);
        verticalLayout.getStyle().set("gap", "0px");
        verticalLayout.setAlignItems(Alignment.CENTER);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setId("about-layout");
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        horizontalLayout.setWidth("60%");
        horizontalLayout.add(createDescription(0, "Burbulams LABAI PATINKA", new String[]{"Didelė oro drėgmė, ypač po stipraus lietaus, arba esant rūkui", "Šlapi, drėgni paviršiai, balos. Burbulai nuostabiai atrodo nutūpę ant balų", "Oro ir tirpalo temperatūra artima kambario temperatūrai t.y. apie 22 ºC", "Apsiniaukusios dienos. Tuomet ne taip greit garuoja vanduo iš muilo sienelės, o burbulų spalvos ryškiausios!", "Švelnus šiltas vos juntamas vėjelis", "Plačios erdvės: parkai, pėsčiųjų tiltai, stadionai, viadukai, kalno viršūnė – kur jie gali nevaržomai toli skristi", "Švarios ir švelnios (medvilninės) virvutės, gerai įmirkusios tirpalu", "Lėti, flegmatiški judesiai lazdelėmis, leidžiant burbulus"}));
        horizontalLayout.add(createDescription(1, "Burbulai LABAI NEMĖGSTA", new String[]{"Putų skysčio paviršiuje. Negalima lazdelėmis plakti burbulų skysčio!", "Nešvaraus vandens arba nešvaraus kibiro; Burbulams labai nepatinka riebalai", "Nešvarių, žemėtų, smėlėtų, žolėm apkibusių virvučių, kai jos braukiamos per žemę", "Smarkaus vėjo arba lietaus", "Sauso oro ir sausų paviršių; Tiesioginių kaitrios saulės spindulių", "Labai karštos ar labai šaltos dienos", "Šalia esančių krūmų, medžių, laužo, dulkių, dūmų, vabzdžių", "Staigių greitų judesių lazdelėmis"}));
        verticalLayout.add(horizontalLayout);
        add(verticalLayout);
    }

    public Component createDescription(int i, String title, String[] paragraphs) {
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setWidth("100%%");

        H4 titleWrapper = new H4(title);
        titleWrapper.getStyle().set("align-self", "center");
        if (i == 0) {
            mainLayout.add(new Icon(VaadinIcon.THUMBS_UP_O), titleWrapper);
        } else {
            mainLayout.add(new Icon(VaadinIcon.THUMBS_DOWN_O), titleWrapper);

        }

        VerticalLayout paragraphWrapper = new VerticalLayout();
        paragraphWrapper.getStyle().set("padding", "0px");
        paragraphWrapper.getStyle().set("gap", "0px");
        paragraphWrapper.getStyle().set("text-align", "justify");


        for (String paragraph : paragraphs) {
            Paragraph p = new Paragraph(paragraph);
            p.getStyle().set("padding", "0px");
            p.getStyle().setMarginTop("0px");
            p.getStyle().setMarginBottom("10px");
            paragraphWrapper.add(p);
        }

        mainLayout.add(paragraphWrapper);
        return mainLayout;
    }

}
