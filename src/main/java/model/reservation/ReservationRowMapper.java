package model.reservation;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReservationRowMapper implements RowMapper<Reservation> {

    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(rs.getInt("id"));
        reservation.setAreaPeriod(rs.getInt("area_period"));
        reservation.setCitizen(rs.getInt("phone"));
        reservation.setCode(rs.getInt("code"));
        reservation.setOccupancy(rs.getInt("occupancy"));
        reservation.setDay(rs.getObject("day", LocalDateTime.class));
        return reservation;
    }
}