package lt.kkava.burbulu_dede.views.shop;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import lt.kkava.burbulu_dede.components.product.ProductViewComponent;
import lt.kkava.burbulu_dede.components.product.ProductViewComponentPhone;
import lt.kkava.burbulu_dede.domain.Product;
import lt.kkava.burbulu_dede.services.BasketService;
import lt.kkava.burbulu_dede.services.ProductService;
import lt.kkava.burbulu_dede.views.MainLayout;
import lt.kkava.burbulu_dede.views.simple.BubblesCollectionView;
import lt.kkava.burbulu_dede.views.simple.ExperimentsCollectionView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope("prototype")
@Route(value = "product_experiments_details", layout = MainLayout.class)
@PageTitle("EKSPERIMANTŲ PARDUOTUVĖ")

public class ExperimentsProductView extends VerticalLayout implements HasUrlParameter<String> {
    private Product product;
    private final ProductService productService;
    private final BasketService basketService;

    public ExperimentsProductView(ProductService productService, BasketService basketService) {
        this.productService = productService;
        this.basketService = basketService;
        getStyle().set("display", "flex");
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if (!isMobile())
            add(new ProductViewComponent(product, basketService, RouteConfiguration.forSessionScope().getUrl(ExperimentsCollectionView.class)));
        else {
            add(new ProductViewComponentPhone(product, basketService, RouteConfiguration.forSessionScope().getUrl(ExperimentsCollectionView.class)));
            this.getStyle().setOverflow(Style.Overflow.HIDDEN);
        }
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String id) {
        for (Product product : productService.getAllExperimentProducts()) {
            if (Objects.equals(product.getItemId(), id)) {
                this.product = product;
                return;
            }
        }
    }

    boolean isMobile(){
        WebBrowser browser = VaadinSession.getCurrent().getBrowser();
        return browser.isAndroid() || browser.isIPhone() || browser.isWindowsPhone();
    }
}
