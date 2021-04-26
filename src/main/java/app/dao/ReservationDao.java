package app.dao;

import app.dao.generic.Dao;
import app.model.Citizen;
import app.model.Reservation;
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
            return jdbc.query("SELECT * FROM Reservation WHERE citizen =?", mapper, citizen.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}