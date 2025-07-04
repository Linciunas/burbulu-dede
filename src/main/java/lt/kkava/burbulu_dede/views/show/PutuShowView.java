package lt.kkava.burbulu_dede.views.show;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("Putų  šou")
@Route(value = "show_putu", layout = MainLayout.class)
public class PutuShowView extends ShowView {
    public PutuShowView() {
        super("images/show/putu/putu_show%02d.jpg", 96, 97);
    }
}
