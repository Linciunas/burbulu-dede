package lt.kkava.burbulu_dede.views.show;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("Ugnies ir dūmų šou")
@Route(value = "show_ugnies_ir_dumu", layout = MainLayout.class)
public class UgniesIrDumuShowView extends ShowView {
    public UgniesIrDumuShowView() {
        super("images/show/ugnies_ir_dumu/ugnies_ir_dumu_show%02d.jpg", 23, 23);
    }
}
