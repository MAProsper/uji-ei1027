package app.dao.reservation;

import app.dao.Dao;
import app.model.reservation.Reservation;
import app.model.reservation.ReservationPeriod;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationPeriodDao extends Dao<ReservationPeriod> {
    public ReservationPeriodDao() {
        super(ReservationPeriod.class);
    }

    public List<ReservationPeriod> getByReservation(Reservation reservation) {
        try {
            return jdbc.query("SELECT * FROM ReservationPeriod WHERE reservation =?", mapper, reservation.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
