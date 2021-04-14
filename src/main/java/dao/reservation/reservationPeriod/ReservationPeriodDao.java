package dao.reservation.reservationPeriod;

import model.reservation.Reservation;
import model.reservation.ReservationPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationPeriodDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(ReservationPeriod reservationPeriod) {
        jdbcTemplate.update("INSERT INTO ReservationPeriod VALUES(?, ?, ?, ?, ?, ?)",
                reservationPeriod.getId(), reservationPeriod.getReservation(), reservationPeriod.getPeriod());
    }

    public void update(ReservationPeriod reservationPeriod) {
        jdbcTemplate.update("UPDATE ReservationPeriod SET id =?, reservation =?, period =?",
                reservationPeriod.getId(), reservationPeriod.getReservation(), reservationPeriod.getPeriod());
    }

    public void delete(ReservationPeriod address) {
        jdbcTemplate.update("DELETE FROM ReservationPeriod WHERE id =?",
                address.getId());
    }

    public List<ReservationPeriod> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM ReservationPeriod", new ReservationPeriodRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public ReservationPeriod getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM ReservationPeriod WHERE id =?", new ReservationPeriodRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<ReservationPeriod> getByReservation(Reservation reservation) {
        try {
            return jdbcTemplate.query("SELECT * FROM ReservationPeriod WHERE reservation =?", new ReservationPeriodRowMapper(), reservation.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}