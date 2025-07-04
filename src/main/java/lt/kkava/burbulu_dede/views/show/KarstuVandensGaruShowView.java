package lt.kkava.burbulu_dede.views.show;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("Karštų vandens garų šou")
@Route(value = "show_karstu_vandens_garu", layout = MainLayout.class)
public class KarstuVandensGaruShowView extends ShowView {
    public KarstuVandensGaruShowView() {
        super("images/show/karstu_vandens_garu/karstu_vandens_garu_show%02d.jpg", 16, 16);
    }
}
