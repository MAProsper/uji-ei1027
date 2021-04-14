package app.dao.service.servicePeriod;

import app.model.service.ServicePeriod;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicePeriodRowMapper implements RowMapper<ServicePeriod> {
    @Override
    public ServicePeriod mapRow(ResultSet resultSet, int i) throws SQLException {
        ServicePeriod servicePeriod = new ServicePeriod();
        servicePeriod.setId(resultSet.getInt("id"));
        servicePeriod.setService(resultSet.getInt("app/service"));
        return servicePeriod;
    }
}
