package app.dao;

import app.dao.generic.Dao;
import app.model.Citizen;
import app.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationDao extends Dao<Reservation> {
    public ReservationDao() {
        super(Reservation.class);
    }

    public List<Reservation> getByCitizen(Citizen citizen) {
        return executeQuery("WHERE citizen = ?", citizen.getId());
    }
}