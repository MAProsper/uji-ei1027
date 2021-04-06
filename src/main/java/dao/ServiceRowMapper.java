package dao;

import model.Service;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ServiceRowMapper implements RowMapper<Service> {
    @Override
    public Service mapRow(ResultSet rs, int i) throws SQLException {
        Service service = new Service();
        service.setId(rs.getInt("id"));
        service.setServiceType(rs.getInt("service_type"));
        service.setArea(rs.getInt("area"));
        return service;
    }
}