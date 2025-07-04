package lt.kkava.burbulu_dede.views.simple;

import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import lt.kkava.burbulu_dede.components.ImageWrapper;
import lt.kkava.burbulu_dede.views.MainLayout;

import java.util.List;

@PageTitle("Sveiki")
@Route(value = "home", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {
    public HomeView() {

        this.setAlignItems(Alignment.CENTER);
        this.setJustifyContentMode(JustifyContentMode.CENTER);

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setWidth("90%");
        mainLayout.getStyle().set("margin", "auto");
        mainLayout.getStyle().set("padding-top", "0px");

        HorizontalLayout mainImageLayout = new HorizontalLayout();
        mainImageLayout.setWidthFull();
        mainImageLayout.setClassName("main-image-layout");

        HorizontalLayout imageLayout1 = new HorizontalLayout();
        imageLayout1.setWidth("100%");
        imageLayout1.setClassName("image-layout");
        imageLayout1.setJustifyContentMode(JustifyContentMode.CENTER);
        imageLayout1.setWidthFull();
        imageLayout1.add(new ImageWrapper("images/home.jpg"), new ImageWrapper("images/home2.jpg"));

        HorizontalLayout imageLayout2 = new HorizontalLayout();
        imageLayout2.setWidth("100%");
        imageLayout2.setClassName("image-layout");
        imageLayout2.setJustifyContentMode(JustifyContentMode.CENTER);
        imageLayout2.setWidthFull();
        imageLayout2.add(new ImageWrapper("images/home3.jpg"), new ImageWrapper("images/home5.jpg"));

        mainImageLayout.add(imageLayout1, imageLayout2);

        mainLayout.add(mainImageLayout);

        this.setClassName("home-paragraph");

        List<String> text = List.of(
                "Taip taip, čia tas pats Burbulų Dėdė, televizijos laidų „GalvOK“ nuolatinis dalyvis, „Lietuvos talentų 2022“ laidų dalyvis, daugybės miestų ir miestelių šventinių programų kūrėjas, ir žinoma, ryškių privačių švenčių (krikštynų, vestuvių, gimtadienių) vedėjas. Sveiki atvykę į mano interneto svetainę!",
                "Čia rasite visą informaciją apie mano veiklą, kontaktus ir nedidelę šventinių prekių parduotuvę. Esu tikras, kad šios prekės papuoš Jūsų šventę ir suteiks daug smagių emocijų.",
                "Burbulų Dėdė gyvena Kaune, bet veda šventes visoje Lietuvoje. Dažniausiai šventes vedame dviese, kartais tenka važiuot ir vienam.",
                "Džiugu prisipažinti, kad jau dešimtus metus rengiu įdomius ir gražius pasirodymus su muilo burbulais, sausuoju ledu, superabsorbentais, spalvotais dūmais ir ryškiomis glitėsių masėmis, kurias dievina vaikai. Per tiek metų sukaupta patirtis ir žinios neabejotinai prisidėjo prie tobulesnės programos kūrimo, įdomesnių triukų, spalvingesnių vaizdų!",
                "Dešimt metų šokau Kauno pramoginių šokių kolektyve „Viesulas“, todėl dievinu pramoginių šokių muziką! Užvedantys Lotynų Amerikos šokių – sambos, čia čia čia, džaivo ritmai labai dažnai girdimi mano vedamose šventėse! Linksma šokių muzika ir ilgi spalvoti burbulų šleifai, tįstantys ore, sukelia tikrą laimės pojūtį!",
                "Labai smagu, kai energiją, kurią spinduliuoju, man sugrįžta atgal – šypsenomis, plojimais, krykštavimais ir juoku.",
                "Man labai smagu, kai žmonės džiaugsmingai reaguoja į tai, ką mato, ir nebijo tai išreikšti – judesiu, šokiu, juoku arba tiesiog pasako komplimentą. Labai tai vertinu! Vasarą rasti laisvo laiko yra be galo sunku, nes nuolat dalyvauju įvairiuose renginiuose ir projektuose arba tvarkausi po jų, vykstu iš tolimųjų kelionių namo. Todėl labai yra svarbu Burbulų Dėdės šventę rezervuoti kaip įmanoma anksčiau. Geriausia sausį–vasarį, kol dar neįsibėgėjo renginių maratonas.",
                "Burbulų Dėdę rasite ir Feisbuke. Labai ačiū visiems sekėjams, draugams, kolegoms ir tiems, kurie nuolat mane kviečia! Tai verčia mane dar labiau pasitempti. Iki burbulingo susitikimo šventėje!"
        );

        for (String s : text) {
            Paragraph p = new Paragraph(s);
            p.getStyle().set("margin", "0px");
            mainLayout.add(p);
        }

        add(mainLayout);
        setSpacing(false);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.START);
        setDefaultHorizontalComponentAlignment(Alignment.START);
    }
}

