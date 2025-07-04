package lt.kkava.burbulu_dede.services;

import lombok.AllArgsConstructor;
import lt.kkava.burbulu_dede.domain.ProduktuUzsakymas;
import lt.kkava.burbulu_dede.services.dao.OrderDao;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderDao orderDao;

    public void save(ProduktuUzsakymas produktuUzsakymas) {
        if (produktuUzsakymas.getId() == null) {
            orderDao.save(produktuUzsakymas);
        } else {
            orderDao.update(produktuUzsakymas);
        }
    }
}
