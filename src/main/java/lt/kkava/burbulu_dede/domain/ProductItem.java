package lt.kkava.burbulu_dede.domain;

import lombok.*;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductItem {
    @With
    private String itemId;
    @With
    private String itemTitle;
    @With
    private int amount = 0;
    @With
    private int itemPrice;
    @With
    private String information;
    @With
    private String imageSrc;
}
