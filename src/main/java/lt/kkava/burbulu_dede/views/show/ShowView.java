package lt.kkava.burbulu_dede.views.show;

import com.vaadin.componentfactory.addons.splide.ImageSlide;
import com.vaadin.componentfactory.addons.splide.Splide;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class ShowView extends VerticalLayout {
    public ShowView(String path, int desktopCounter, int phoneCounter) {
        if (isMobile()) {
            createPhoneLayout(path, phoneCounter);
        } else {
            createDesktopLayout(path, desktopCounter);
        }
    }

    public boolean isMobile() {
        WebBrowser browser = VaadinSession.getCurrent().getBrowser();
        return browser.isAndroid() || browser.isIPhone() || browser.isWindowsPhone();
    }

    public void createDesktopLayout(String path, int counter) {
        HorizontalLayout sliderLayoutContainer = new HorizontalLayout();
        //thumbnails-slider-images-slider
        sliderLayoutContainer.setSpacing(false);
        sliderLayoutContainer.setWidth("50%");
        setHeight("95%");
        sliderLayoutContainer.setHeight("750px");

        sliderLayoutContainer.getElement().getStyle().set("margin", "auto");

        ImageSlide[] images = new ImageSlide[counter];
        String imgSrc;

        for (int i = 0; i < counter; i++) {
            imgSrc = String.format(path, i + 1);
            images[i] = new ImageSlide(imgSrc);
        }

        Splide slider = new Splide(Arrays.asList(images));
        slider.setId("images-slider");
        slider.setWidthFull();
        slider.setHeightFull();

        sliderLayoutContainer.add(slider);

        add(sliderLayoutContainer);
    }

    public void createPhoneLayout(String path, int counter) {
        HorizontalLayout sliderLayoutContainer = new HorizontalLayout();
        path = formatPath(path);
        setHeight("80%");
        //thumbnails-slider-images-slider
        sliderLayoutContainer.setSpacing(false);
        sliderLayoutContainer.setWidth("290px");
        sliderLayoutContainer.setHeight("400px");

        sliderLayoutContainer.getElement().getStyle().set("margin", "auto");

        ImageSlide[] images = new ImageSlide[counter];
        String imgSrc;

        for (int i = 0; i < counter; i++) {
            imgSrc = String.format(path, i + 1);
            images[i] = new ImageSlide(imgSrc);
        }

        Splide slider = new Splide(Arrays.asList(images));
        slider.setId("images-slider");
        slider.setWidthFull();
        slider.setHeightFull();

        sliderLayoutContainer.add(slider);

        add(sliderLayoutContainer);
    }

    //"images/show/sauso_ledo/sauso_ledo_show%02d.jpg"
    //0,1,2,
    public String formatPath(String path) {
        String[] arrOfStr = path.split("/");
        String newPath = "";
        for (int i = 0; i < arrOfStr.length; i++) {
            if (i == 2) {
                newPath += "phone/";
            }
            if (i == arrOfStr.length - 1) {
                newPath += arrOfStr[i];
            } else {
                newPath += arrOfStr[i] + "/";
            }
        }
        return newPath;
    }
}

