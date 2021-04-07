package dao;

import model.ServicePeriod;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ServicePeriodRowMapper implements RowMapper<ServicePeriod> {
    @Override
    public ServicePeriod mapRow(ResultSet resultSet, int i) throws SQLException {
        ServicePeriod servicePeriod = new ServicePeriod();
        servicePeriod.setId(resultSet.getInt("id"));
        servicePeriod.setService(resultSet.getInt("service"));
        return servicePeriod;
    }
}
