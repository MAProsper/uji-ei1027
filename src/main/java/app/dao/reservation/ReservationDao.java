package app.dao.reservation;

import app.dao.Dao;
import app.model.citizen.Citizen;
import app.model.reservation.Reservation;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationDao extends Dao<Reservation> {
    public ReservationDao() {
        super(Reservation.class);
    }

    public List<Reservation> getByCitizen(Citizen citizen) {
        try {
            return jdbc.query("SELECT * FROM Reservation WHERE citizen =?", mapper, citizen.getPerson());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}