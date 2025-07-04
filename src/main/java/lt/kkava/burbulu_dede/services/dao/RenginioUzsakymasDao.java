package lt.kkava.burbulu_dede.services.dao;

import com.mongodb.client.MongoDatabase;
import lt.kkava.burbulu_dede.domain.RenginioUzsakymas;
import lt.kkava.burbulu_dede.domain.UzsakymasSearchCriteria;
import lt.kkava.common_utils.services.DaoService;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenginioUzsakymasDao extends DaoService<RenginioUzsakymas, UzsakymasSearchCriteria> {
    static final String TABLE_NAME = "uzasakymai";

    public RenginioUzsakymasDao(MongoDatabase mongoDatabase) {
        super(mongoDatabase);
    }

    @Override
    public Bson processCriteria(UzsakymasSearchCriteria uzsakymasSearchCriteria) {
        return null;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
