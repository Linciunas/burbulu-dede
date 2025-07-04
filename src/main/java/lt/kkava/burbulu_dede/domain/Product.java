package lt.kkava.burbulu_dede.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.codecs.pojo.annotations.BsonIgnore;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private String itemId;
    private String itemTitle;
    @BsonIgnore
    private String imgSrc;
    @BsonIgnore
    private String[] paragraphsText;
    private String[] instructions;
    private ArrayList<ProductItem> items = new ArrayList<>();
}