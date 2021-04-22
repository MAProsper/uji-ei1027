package app.dao.reservation;

import app.dao.Dao;
import app.model.citizen.Citizen;
import app.model.reservation.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class ReservationDao extends Dao<Reservation> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(Reservation reservation) {
        jdbcTemplate.update("INSERT INTO Reservation VALUES(?, ?, ?, ?, ?, ?, ?)",
                reservation.getId(), reservation.getCitizen(), reservation.getCode(), reservation.getOccupancy(), reservation.getAreaPeriod(), reservation.getDate());
    }

    public void update(Reservation reservation) {
        jdbcTemplate.update("UPDATE Reservation SET id =?, citizen =?, code =?, occupancy =?, area_period =?, date =?",
                reservation.getId(), reservation.getCitizen(), reservation.getCode(), reservation.getOccupancy(), reservation.getAreaPeriod(), reservation.getDate());
    }

    public List<Reservation> getByCitizen(Citizen citizen) {
        try {
            return jdbcTemplate.query("SELECT * FROM Reservation WHERE citizen =?", new ReservationRowMapper(), citizen.getPerson());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}