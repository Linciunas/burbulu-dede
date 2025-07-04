package lt.kkava.burbulu_dede.components.order;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lt.kkava.burbulu_dede.views.simple.BubblesCollectionView;
import lt.kkava.common_utils.ui.services.UtilitiesUI;

public class EmptyShoppingCartComponent extends VerticalLayout {
    public EmptyShoppingCartComponent() {
        this.setAlignItems(FlexComponent.Alignment.CENTER);
        this.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        mainLayout.setSpacing(false);
        mainLayout.getThemeList().add("spacing-xl");

        H3 text = new H3("Jūsų krepšelis yra tuščias");
        text.getStyle().set("letter-spacing", "4px");

        Button buttonBackToShop = new Button("Mūsų parduotuvėje visada yra idomybių", e -> {
            UtilitiesUI.forwardTo(BubblesCollectionView.class);
        });
        buttonBackToShop.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


        mainLayout.add(text, buttonBackToShop);
        add(mainLayout);
    }
}
