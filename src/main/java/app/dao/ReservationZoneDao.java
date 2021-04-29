package app.dao;

import app.dao.generic.Dao;
import app.model.ReservationZone;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationZoneDao extends Dao<ReservationZone> {
    public List<ReservationZone> getByReservation(int id) {
        return getByField("reservation", id);
    }
}
