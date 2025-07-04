package lt.kkava.burbulu_dede.services;

import lombok.AllArgsConstructor;
import lt.kkava.burbulu_dede.domain.Product;
import lt.kkava.burbulu_dede.domain.ProductItem;
import lt.kkava.burbulu_dede.domain.ProduktuUzsakymas;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
@Component
public class BasketService {

    public void updateBasket(ProduktuUzsakymas produktuUzsakymas, Product product) {
        if (produktuUzsakymas == null || product == null) {
            return;
        }

        if(produktuUzsakymas.getProductItems().isEmpty()){
            for(ProductItem item: product.getItems()){
                if(item.getAmount() != 0) {
                    produktuUzsakymas.getProductItems().add(item);
                }
            }
        } else {
            for(ProductItem item : product.getItems()) {
                boolean found = false;
                for(ProductItem produktuUzsakymasItem : produktuUzsakymas.getProductItems()) {
                    if(Objects.equals(item.getItemId(), produktuUzsakymasItem.getItemId())) {
                        found = true;
                        if(item.getAmount() > produktuUzsakymasItem.getAmount() && produktuUzsakymasItem.getAmount() == 0) {
                            produktuUzsakymas.getProductItems().add(item);
                        } else if(item.getAmount() != 0){
                            produktuUzsakymas.getProductItems().get(produktuUzsakymas.getProductItems().indexOf(produktuUzsakymasItem)).setAmount(item.getAmount());
                        }
                    }
                }
                if(!found && item.getAmount() != 0) {
                    produktuUzsakymas.getProductItems().add(item);
                }
            }
        }
    }
}
