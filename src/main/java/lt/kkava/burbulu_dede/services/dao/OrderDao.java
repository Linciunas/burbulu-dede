package lt.kkava.burbulu_dede.services.dao;

import com.mongodb.client.MongoDatabase;
import lt.kkava.burbulu_dede.domain.ProduktuUzsakymas;
import lt.kkava.burbulu_dede.domain.ProduktuUzsakymasSearchCriteria;
import lt.kkava.common_utils.services.DaoService;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

@Service
public class OrderDao extends DaoService<ProduktuUzsakymas, ProduktuUzsakymasSearchCriteria> {
    private static final String TABLE_NAME = "orders";

    public OrderDao(MongoDatabase mongoDatabase) {
        super(mongoDatabase);
    }

    @Override
    public Bson processCriteria(ProduktuUzsakymasSearchCriteria produktuUzsakymasSearchCriteria) {
        return null;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
