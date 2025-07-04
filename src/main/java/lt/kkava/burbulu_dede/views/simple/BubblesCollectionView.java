package lt.kkava.burbulu_dede.views.simple;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.components.collection.BubblesCollectionViewComponent;
import lt.kkava.burbulu_dede.domain.Product;
import lt.kkava.burbulu_dede.services.ProductService;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("BURBULŲ PARDUOTUVĖ")
@Route(value = "store_bubbles", layout = MainLayout.class)
public class BubblesCollectionView extends VerticalLayout {
    public BubblesCollectionView(ProductService productService) {
        this.setAlignItems(Alignment.CENTER);
        VerticalLayout cards = new VerticalLayout();
        setSpacing(false);
        cards.getStyle().set("text-align", "center");
        cards.setId("collections-layout");
        for (Product product : productService.getAllBubbleProducts()) {
            cards.add(new BubblesCollectionViewComponent(product));
        }

        cards.setJustifyContentMode(JustifyContentMode.CENTER);
        cards.setDefaultHorizontalComponentAlignment(Alignment.START);
        Text footer = new Text("Visos kainos nurodytos su įskaičiuotais mokesčiais.\n" +
                "Turite klausimų?  burbuludede@gmail.com\n");
        add(cards, footer);
    }

}
