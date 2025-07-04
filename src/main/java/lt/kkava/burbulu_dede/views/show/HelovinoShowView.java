package lt.kkava.burbulu_dede.views.show;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("Helovino šou")
@Route(value = "show_helovino", layout = MainLayout.class)
public class HelovinoShowView extends ShowView {
    public HelovinoShowView() {
        super("images/show/helovino/helovino_show%02d.jpg", 13, 13);
    }
}
