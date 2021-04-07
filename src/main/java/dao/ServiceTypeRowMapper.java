package dao;

import model.ServiceType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ServiceTypeRowMapper implements RowMapper<ServiceType> {
    @Override
    public ServiceType mapRow(ResultSet resultSet, int i) throws SQLException {
        ServiceType serviceType = new ServiceType();
        serviceType.setId(resultSet.getInt("id"));
        serviceType.setType(resultSet.getString("type"));
        serviceType.setDescription(resultSet.getString("description"));
        return serviceType;
    }
}
