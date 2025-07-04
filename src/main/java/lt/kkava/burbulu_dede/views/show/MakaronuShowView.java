package lt.kkava.burbulu_dede.views.show;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("Makaronų  šou")
@Route(value = "show_makaronu", layout = MainLayout.class)
public class MakaronuShowView extends ShowView {
    public MakaronuShowView() {
        super("images/show/makaronu/makaronu_show%02d.jpg", 23, 23);
    }
}
