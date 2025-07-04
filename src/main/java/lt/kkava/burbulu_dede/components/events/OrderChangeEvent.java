package lt.kkava.burbulu_dede.components.events;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import lombok.Getter;
import lt.kkava.burbulu_dede.domain.Product;
import lt.kkava.burbulu_dede.domain.ProductItem;

import java.util.LinkedList;
import java.util.List;

@Getter
public class OrderChangeEvent extends ComponentEvent<Component> {
    private final boolean plus;
    private final boolean minus;
    private final boolean delete;
    private ProductItem ObjectProductItem;

    public OrderChangeEvent(Component source, boolean fromClient, boolean plus, boolean minus, boolean delete, ProductItem productItem) {
        super(source, fromClient);
        this.plus = plus;
        this.minus = minus;
        this.delete = delete;
        this.ObjectProductItem = productItem;
    }
}
