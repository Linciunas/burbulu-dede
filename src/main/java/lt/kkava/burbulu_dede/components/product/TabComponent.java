package lt.kkava.burbulu_dede.components.product;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import lt.kkava.burbulu_dede.domain.Product;

public class TabComponent extends Div {
    private final Tab description;
    private final Tab instructions;
    private final VerticalLayout content = new VerticalLayout();
    private final Tabs tabs = new Tabs();
    private final Product product;
    public TabComponent(Product product) {
        this.product = product;
        this.setMaxWidth("929px");
        tabs.setWidthFull();
        content.setWidthFull();
        content.getStyle().set("gap","0px");
        content.getStyle().set("text-wrap","wrap");

        description = new Tab("Aprašymas");
        instructions = new Tab("Instrukcija");

        description.setEnabled(false);
        instructions.setEnabled(false);

        tabs.addSelectedChangeListener(
                event -> setContent(event.getSelectedTab(), product));
        tabs.add(description, instructions);
        add(tabs, content);
    }

    public void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        if(product.getParagraphsText().length == 0 && product.getInstructions().length == 0){
            description.setEnabled(false);
            instructions.setEnabled(false);
        } else {
            if(product.getParagraphsText().length == 0){
                instructions.setEnabled(true);
                tabs.setSelectedTab(instructions);
            } else if (product.getInstructions().length == 0){
                description.setEnabled(true);
                tabs.setSelectedTab(description);
            } else {
                description.setEnabled(true);
                instructions.setEnabled(true);
                tabs.setSelectedTab(description);
            }
        }
    }

    private void setContent(Tab tab, Product product) {
        content.removeAll();
        if (tab == null) {
            return;
        }
        if (tab.equals(description)) {
            for(String text : product.getParagraphsText()) {
                content.add(new Paragraph(text));
            }
        } else {
            for(String text : product.getInstructions()) {
                content.add(new Paragraph(text));
            }
            Anchor anchor = new Anchor(getInstructionSrc(product), "Atidaryti instrukcijas pdf formatų");
            anchor.getStyle().set("text-decoration", "none");
            anchor.getElement().setAttribute("router-ignore", true);
            anchor.setTarget("_blank");

            content.add(anchor);
        }
    }

    public String getInstructionSrc(Product product) {
        return switch (product.getItemId()) {
            case "burbulu_milteliai" -> "/assets/burbulu_milteliai_instrukcija.pdf";
            case "kirmeles" -> "/assets/kirmeliu_instrukcija.pdf";
            case "drebuciai" -> "/assets/drebuciu_instrukcija.pdf";
            case "pampersinis_sniegas" -> "/assets/pampersinis_sniegas.pdf";
            case "dregnas_sniegas" -> "/assets/dregnas_sniegas_instrukcija.pdf";
            case "sausas_sniegas" -> "/assets/sausas_sniegas_instrukcija.pdf";
            case "burbulu_milteliai_masinoms" -> "/assets/masinu_instrukcija.pdf";
            case "orbeez" -> "/assets/orbeez_instrukcija.pdf";
            default -> "";
        };
    }
}
