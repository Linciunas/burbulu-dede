package lt.kkava.burbulu_dede.views.show;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("Repeticijos")
@Route(value = "show_repeticijos", layout = MainLayout.class)
public class RepeticijosShowView extends ShowView {
    public RepeticijosShowView() {
        super("images/show/repeticijos/repeticijos_show%02d.jpg", 13, 13);
    }
}
