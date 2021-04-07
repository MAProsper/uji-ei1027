package dao;

import model.ReservationPeriod;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ReservationPeriodRowMapper implements RowMapper<ReservationPeriod> {
    @Override
    public ReservationPeriod mapRow(ResultSet resultSet, int i) throws SQLException {
        ReservationPeriod reservationPeriod = new ReservationPeriod();
        reservationPeriod.setId(resultSet.getInt("id"));
        reservationPeriod.setReservation(resultSet.getInt("reservation"));
        reservationPeriod.setPeriod(resultSet.getInt("period"));
        return reservationPeriod;
    }
}
