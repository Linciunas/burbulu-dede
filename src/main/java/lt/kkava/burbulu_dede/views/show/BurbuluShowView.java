
package lt.kkava.burbulu_dede.views.show;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("Burbulų šou")
@Route(value = "show_burbulu", layout = MainLayout.class)
public class BurbuluShowView extends ShowView {
    public BurbuluShowView() {
        super("images/show/burbulu/burbulu_show%02d.jpg", 40, 55);
    }
}
