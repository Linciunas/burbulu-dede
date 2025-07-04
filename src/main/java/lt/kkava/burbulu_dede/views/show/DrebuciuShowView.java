package lt.kkava.burbulu_dede.views.show;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("Drebučių  šou")
@Route(value = "show_drebuciu", layout = MainLayout.class)
public class DrebuciuShowView extends ShowView {
    public DrebuciuShowView() {
        super("images/show/drebuciu/drebuciu_show%02d.jpg", 56, 61);
    }
}
