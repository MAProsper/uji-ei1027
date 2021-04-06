package dao;

import model.Period;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PeriodRowMapper implements RowMapper<Period> {

    public Period mapRow(ResultSet rs, int rowNum) throws SQLException {
        Period period = new Period();
        period.setId(rs.getInt("id"));
        period.setStart(rs.getObject("start", LocalDateTime.class));
        period.setFinish(rs.getObject("finish", LocalDateTime.class));
        return period;
    }
}