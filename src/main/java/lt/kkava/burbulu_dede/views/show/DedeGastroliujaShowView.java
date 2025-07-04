package lt.kkava.burbulu_dede.views.show;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("Dėdė gastroliuoja")
@Route(value = "show_dede_gastroliuja", layout = MainLayout.class)
public class DedeGastroliujaShowView extends ShowView {
    public DedeGastroliujaShowView() {
        super("images/show/dede_gastroliuoja/dede_gastroliuoja_show%02d.jpg", 13, 12);
    }
}
