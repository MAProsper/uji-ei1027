package app.dao.reservation;

import app.dao.Dao;
import app.model.reservation.Reservation;
import app.model.reservation.ReservationZone;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationZoneDao extends Dao<ReservationZone> {
    public ReservationZoneDao() {
        super(ReservationZone.class);
    }

    public List<ReservationZone> getByReservation(Reservation reservation) {
        try {
            return jdbc.query("SELECT * FROM ReservationZone WHERE reservation =?", mapper, reservation.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
