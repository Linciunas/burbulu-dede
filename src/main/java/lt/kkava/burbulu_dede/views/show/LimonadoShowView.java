package lt.kkava.burbulu_dede.views.show;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("Limonadų šou")
@Route(value = "show_limonadu", layout = MainLayout.class)
public class LimonadoShowView extends ShowView {
    public LimonadoShowView() {
        super("images/show/limonadu/limonado_show%02d.jpg", 2, 2);
    }
}
