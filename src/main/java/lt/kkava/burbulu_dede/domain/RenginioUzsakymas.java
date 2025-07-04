package lt.kkava.burbulu_dede.domain;

import lombok.*;
import lt.kkava.common_utils.domain.DaoObject;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RenginioUzsakymas extends DaoObject {
    private ObjectId id;
    private LocalDateTime created = LocalDateTime.now();
    @With
    private String renginioTipas;
    @With
    private String renginioVieta;
    @With
    private LocalDateTime renginioLaikas;
    @With
    private String renginioZmones;
    @With
    private String pasirodymoTrukme;
    @With
    private String name;
    @With
    private String email;
    @With
    private String phone;
}
