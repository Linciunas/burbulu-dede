package lt.kkava.burbulu_dede.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import com.vaadin.flow.theme.lumo.LumoUtility;
import lt.kkava.burbulu_dede.views.simple.*;
import lt.kkava.burbulu_dede.views.show.*;

public class MainLayout extends AppLayout {

    private H1 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        VerticalLayout headerLayout = new VerticalLayout();
        headerLayout.setWidthFull();
        headerLayout.getStyle().set("height","auto");
        headerLayout.getStyle().set("padding-top","0px");
        headerLayout.getStyle().set("padding-bottom","14px");

        headerLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        headerLayout.setSpacing(false);

        HorizontalLayout topHeaderLayout = new HorizontalLayout();
        topHeaderLayout.setWidthFull();
        topHeaderLayout.setHeight("80px");
        topHeaderLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        topHeaderLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        DrawerToggle toggle = new DrawerToggle();
        toggle.getStyle().set("margin","0px");
        toggle.setAriaLabel("Menu toggle");

        Image logo = new Image("images/logo.png", "Burbulų dėdė logo");
        logo.addClassName("logo");

        HorizontalLayout iconWrapper = new HorizontalLayout();
        iconWrapper.setWidth("48px");
        iconWrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        iconWrapper.add(initShopContent());
        topHeaderLayout.add(toggle, logo, iconWrapper);

        HorizontalLayout bottomHeaderLayout = new HorizontalLayout();
        bottomHeaderLayout.setWidthFull();
        bottomHeaderLayout.setHeight("40px");
        bottomHeaderLayout.getStyle().set("padding-top","14px");
        bottomHeaderLayout.getStyle().set("border-top","0.1px solid  rgb(0, 95, 219)");
        bottomHeaderLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        viewTitle = new H1();
        viewTitle.setId("viewTitle");
        if(!isMobile()) viewTitle.getStyle().set("font-size","var(--lumo-font-size-xl)");
        else {
            viewTitle.getStyle().set("font-size","var(--lumo-font-size-s)");
            bottomHeaderLayout.getStyle().set("padding-top","20px");
        }
        viewTitle.getStyle().set("letter-spacing","4px");
        viewTitle.getStyle().set("font-family","ui-sans-serif");
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        bottomHeaderLayout.add(viewTitle);

        headerLayout.add(topHeaderLayout, bottomHeaderLayout);

        addToNavbar(false, headerLayout);
    }

    private Component initShopContent() {
        HorizontalLayout shopLayout = new HorizontalLayout();
        shopLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        shopLayout.setId("navbar-shop-layout");

        String route = RouteConfiguration.forSessionScope().getUrl(OrderView.class);
        Anchor link = new Anchor(route);

        Icon shopIcon = VaadinIcon.CART_O.create();
        link.add(shopIcon);

        shopLayout.add(link);
        return shopLayout;
    }

    private void addDrawerContent() {
        Scroller scroller = new Scroller(createNavigation());
        addToDrawer(scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("Pradžia", HomeView.class));

        SideNavItem shopSection = new SideNavItem("Parduotuvė");
        shopSection.addItem(new SideNavItem("Burbulų parduotuvė", BubblesCollectionView.class));
        shopSection.addItem(new SideNavItem("Eksperimentų parduotuvė", ExperimentsCollectionView.class));
        SideNavItem showSection = new SideNavItem("Šou");
        showSection.addItem(new SideNavItem("Burbulų šou", BurbuluShowView.class));
        showSection.addItem(new SideNavItem("Putų šou", PutuShowView.class));
        showSection.addItem(new SideNavItem("Drebučių šou", DrebuciuShowView.class));
        showSection.addItem(new SideNavItem("Makaronų šou", MakaronuShowView.class));
        showSection.addItem(new SideNavItem("Kristalų šou", KristaluShowView.class));
        showSection.addItem(new SideNavItem("Sausojo ledo šou", SausoLedoShowView.class));
        showSection.addItem(new SideNavItem("Stebuklingo sniego šou", StebuklingoSniegoShowView.class));
        showSection.addItem(new SideNavItem("Limonadų šou", LimonadoShowView.class));
        showSection.addItem(new SideNavItem("Dūminių burbulų šou", DuminiuBurbuluShowView.class));
        showSection.addItem(new SideNavItem("Ugnies ir dūmų šou", UgniesIrDumuShowView.class));
        showSection.addItem(new SideNavItem("Ultravioletinis šou", UltravioletinisShowView.class));
        showSection.addItem(new SideNavItem("Šou cirke", CirkoShowView.class));
        showSection.addItem(new SideNavItem("Šou LT talentuose", TalentaiShowView.class));
        showSection.addItem(new SideNavItem("Helovino šou", HelovinoShowView.class));
        showSection.addItem(new SideNavItem("Dėdė gastroliuoja", DedeGastroliujaShowView.class));
        showSection.addItem(new SideNavItem("Repeticijos", RepeticijosShowView.class));
        showSection.addItem(new SideNavItem("Karštų vandens garų šou", KarstuVandensGaruShowView.class));

        nav.addItem(shopSection, showSection);

        nav.addItem(new SideNavItem("Apie burbulus", AboutView.class));
        nav.addItem(new SideNavItem("Užsakyti šventę", RenginioUzsakymasView.class));
        nav.addItem(new SideNavItem("Kontaktai", ContactsView.class));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle().toUpperCase());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }

    public boolean isMobile() {
        WebBrowser browser = VaadinSession.getCurrent().getBrowser();
        return browser.isAndroid() || browser.isIPhone() || browser.isWindowsPhone();
    }
}
