package lt.kkava.burbulu_dede.views.show;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("Ultravioletinis šou")
@Route(value = "show_ultravioletinis", layout = MainLayout.class)
public class UltravioletinisShowView extends ShowView {
    public UltravioletinisShowView() {
        super("images/show/ultravioletinis/ultravioletinis_show%02d.jpg", 29,30);
    }
}
