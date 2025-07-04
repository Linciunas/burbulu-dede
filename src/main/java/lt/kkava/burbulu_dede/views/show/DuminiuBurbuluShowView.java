package lt.kkava.burbulu_dede.views.show;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("Dūminių burbulų šou")
@Route(value = "show_duminiu_burbulu", layout = MainLayout.class)
public class DuminiuBurbuluShowView extends ShowView {
    public DuminiuBurbuluShowView() {
        super("images/show/duminiu_burbulu/duminiu_burbulu_show%02d.jpg", 24, 24);
    }
}
