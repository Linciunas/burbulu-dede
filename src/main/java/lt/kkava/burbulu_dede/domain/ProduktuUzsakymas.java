package lt.kkava.burbulu_dede.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lt.kkava.common_utils.domain.DaoObject;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduktuUzsakymas extends DaoObject {
    private ObjectId id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String mode;
    private LocalDateTime created = LocalDateTime.now();
    private List<ProductItem> productItems = new LinkedList<>();
}
