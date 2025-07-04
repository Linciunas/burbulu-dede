package lt.kkava.burbulu_dede.views.show;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("Kristalų  šou")
@Route(value = "show_kristalu", layout = MainLayout.class)
public class KristaluShowView extends ShowView {
    public KristaluShowView() {
        super("images/show/kristalu/kristalu_show%02d.jpg", 14, 14);
    }
}
