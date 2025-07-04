package lt.kkava.burbulu_dede.views.show;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("Sausojo ledo  šou")
@Route(value = "show_sauso_ledo", layout = MainLayout.class)
public class SausoLedoShowView extends ShowView {
    public SausoLedoShowView() {
        super("images/show/sauso_ledo/sauso_ledo_show%02d.jpg", 62, 63);
    }
}
