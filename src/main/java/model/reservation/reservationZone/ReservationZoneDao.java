package model.reservation.reservationZone;

import model.reservation.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ReservationZoneDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(ReservationZone reservationZone) {
        jdbcTemplate.update("INSERT INTO ReservationZone VALUES(?, ?, ?)",
                reservationZone.getId(), reservationZone.getReservation(), reservationZone.getZone());
    }

    public void update(ReservationZone reservationZone) {
        jdbcTemplate.update("UPDATE ReservationZone SET id =?, reservation =?, zone =?",
                reservationZone.getId(), reservationZone.getReservation(), reservationZone.getZone());
    }

    public void delete(ReservationZone reservationZone) {
        jdbcTemplate.update("DELETE FROM ReservationZone WHERE id =?",
                reservationZone.getId());
    }

    public List<ReservationZone> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM ReservationZone", new ReservationZoneRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public ReservationZone get(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ReservationZone WHERE id =?", new ReservationZoneRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<ReservationZone> get(Reservation reservation) {
        try {
            return jdbcTemplate.query("SELECT * FROM ReservationZone WHERE reservation =?", new ReservationZoneRowMapper(), reservation.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
