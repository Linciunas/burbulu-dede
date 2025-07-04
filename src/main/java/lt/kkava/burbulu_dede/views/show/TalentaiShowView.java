package lt.kkava.burbulu_dede.views.show;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lt.kkava.burbulu_dede.views.MainLayout;

@PageTitle("Šou LT talentuose")
@Route(value = "show_talentai", layout = MainLayout.class)
public class TalentaiShowView extends ShowView {
    public TalentaiShowView() {
        super("images/show/talentai/talentai_show%02d.jpg", 13, 13);
    }
}
