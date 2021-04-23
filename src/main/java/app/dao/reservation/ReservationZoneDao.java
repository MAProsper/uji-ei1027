package app.dao.reservation;

import app.dao.Dao;
import app.model.reservation.Reservation;
import app.model.reservation.ReservationZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ReservationZoneDao extends Dao<ReservationZone> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(ReservationZone reservationZone) {
        jdbcTemplate.update("INSERT INTO ReservationZone VALUES(?, ?, ?)",
                reservationZone.getId(), reservationZone.getReservation(), reservationZone.getZone());
    }

    public void update(ReservationZone reservationZone) {
        jdbcTemplate.update("UPDATE ReservationZone SET id =?, reservation =?, zone =?",
                reservationZone.getId(), reservationZone.getReservation(), reservationZone.getZone());
    }

    public List<ReservationZone> getByReservation(Reservation reservation) {
        try {
            return jdbcTemplate.query("SELECT * FROM ReservationZone WHERE reservation =?", new ReservationZoneRowMapper(), reservation.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
