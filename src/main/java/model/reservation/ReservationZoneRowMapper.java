package model.reservation;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationZoneRowMapper implements RowMapper<ReservationZone> {
    @Override
    public ReservationZone mapRow(ResultSet resultSet, int i) throws SQLException {
        ReservationZone reservationZone = new ReservationZone();
        reservationZone.setId(resultSet.getInt("id"));
        reservationZone.setReservation(resultSet.getInt("reservation"));
        reservationZone.setZone(resultSet.getInt("zone"));
        return reservationZone;
    }
}
