package lt.kkava.burbulu_dede.views.simple;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import lt.kkava.burbulu_dede.components.order.EmptyShoppingCartComponent;
import lt.kkava.burbulu_dede.components.order.PrekiuUzsakymasComponent;
import lt.kkava.burbulu_dede.domain.Constants;
import lt.kkava.burbulu_dede.domain.ProduktuUzsakymas;
import lt.kkava.burbulu_dede.services.EmailServiceBurbulai;
import lt.kkava.burbulu_dede.services.OrderService;
import lt.kkava.burbulu_dede.views.MainLayout;
import lt.kkava.common_utils.ui.services.UtilitiesUI;

@PageTitle("Pirkinių krepšelis")
@Route(value = "purchase", layout = MainLayout.class)
public class OrderView extends VerticalLayout {
    public OrderView(UtilitiesUI utilitiesUI, OrderService orderService, EmailServiceBurbulai emailServiceBurbulai) {
        setSpacing(false);
        getStyle().set("text-align", "center");
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.START);
        setDefaultHorizontalComponentAlignment(Alignment.START);

        ProduktuUzsakymas produktuUzsakymas = (ProduktuUzsakymas) VaadinSession.getCurrent().getSession().getAttribute(Constants.SESSION_ORDER);
        if (produktuUzsakymas == null || produktuUzsakymas.getProductItems().isEmpty()) {
            add(new EmptyShoppingCartComponent());
            setJustifyContentMode(JustifyContentMode.CENTER);
        } else {
            if (isMobile()) {
                getStyle().set("padding", "0px");
                add(new PhoneOrderView(produktuUzsakymas, utilitiesUI, orderService, emailServiceBurbulai));
            } else {
                add(new PrekiuUzsakymasComponent(produktuUzsakymas, utilitiesUI, orderService, emailServiceBurbulai));
            }
        }
    }

    public boolean isMobile() {
        WebBrowser browser = VaadinSession.getCurrent().getBrowser();
        return browser.isAndroid() || browser.isIPhone() || browser.isWindowsPhone();
    }

}
