package lt.kkava.burbulu_dede.services;

import lombok.AllArgsConstructor;
import lt.kkava.burbulu_dede.domain.RenginioUzsakymas;
import lt.kkava.burbulu_dede.services.dao.RenginioUzsakymasDao;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RenginioUzsakymasService {
    private final RenginioUzsakymasDao renginioUzsakymasDao;

    public void save(RenginioUzsakymas renginioUzsakymas) {
        if (renginioUzsakymas.getId() == null) {
            renginioUzsakymasDao.save(renginioUzsakymas);
        } else {
            renginioUzsakymasDao.update(renginioUzsakymas);
        }
    }
}

//slider, email, checkout, #mittente, subject, body, freemaker