package lt.kkava.burbulu_dede.views.show;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("Šou cirke")
@Route(value = "show_cirke", layout = MainLayout.class)
public class CirkoShowView extends ShowView {
    public CirkoShowView() {
        super("images/show/cirke/cirke_show%02d.jpg", 9, 9);
    }
}
