package lt.kkava.burbulu_dede.views.show;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("Stebuklingo sniego šou")
@Route(value = "show_stebuklingo_sniego", layout = MainLayout.class)
public class StebuklingoSniegoShowView extends ShowView {
    public StebuklingoSniegoShowView() {
        super("images/show/stebuklingo_sniego/stebuklingo_sniego_show%02d.jpg", 14, 15);
    }
}
