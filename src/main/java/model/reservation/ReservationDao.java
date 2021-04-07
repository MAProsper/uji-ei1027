package model.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(Reservation reservation) {
        jdbcTemplate.update("INSERT INTO Reservation VALUES(?, ?, ?, ?, ?, ?)",
                reservation.getId(), reservation.getCitizen(), reservation.getCode(), reservation.getOccupancy(), reservation.getAreaPeriod());
    }

    public void update(Reservation reservation) {
        jdbcTemplate.update("UPDATE Reservation SET id =?, citizen =?, code =?, occupancy =?, area_period =?",
                reservation.getId(), reservation.getCitizen(), reservation.getCode(), reservation.getOccupancy(), reservation.getAreaPeriod());
    }

    public void delete(Reservation reservation) {
        jdbcTemplate.update("DELETE FROM Reservation WHERE id =?",
                reservation.getId());
    }

    public List<Reservation> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM Reservation", new ReservationRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public Reservation get(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Reservation WHERE id =?", new ReservationRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}