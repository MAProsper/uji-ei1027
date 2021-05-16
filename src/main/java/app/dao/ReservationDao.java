package app.dao;

import app.dao.generic.Dao;
import app.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ReservationDao extends Dao<Reservation> {
    public List<Reservation> getByCitizen(int id) {
        return getByField("citizen", id);
    }
}