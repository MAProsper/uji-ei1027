package app.dao;

import app.dao.generic.Dao;
import app.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationDao extends Dao<Reservation> {
    public List<Reservation> getByCitizen(int id) {
        return getByField("citizen", id);
    }

    public List<Reservation> getByAreaPeriod(int id) {
        return getByField("areaPeriod", id);
    }
}