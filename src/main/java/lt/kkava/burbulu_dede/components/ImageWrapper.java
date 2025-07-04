package lt.kkava.burbulu_dede.components;


import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class ImageWrapper extends VerticalLayout {
    public ImageWrapper(String imageSrc) {
        setSpacing(false);

        this.setWidth("auto");


        Image img = new Image();
        img.setSrc(imageSrc);
        img.setClassName("home-image");
        img.setWidth("300px");
        img.setHeight("100%");

        setPadding(false);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");

        add(img);
    }
}
