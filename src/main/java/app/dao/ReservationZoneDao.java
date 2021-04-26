package app.dao;

import app.dao.generic.Dao;
import app.model.ReservationZone;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationZoneDao extends Dao<ReservationZone> {
    public ReservationZoneDao() {
        super(ReservationZone.class);
    }
}
