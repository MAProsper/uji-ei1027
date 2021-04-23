package app.dao.municipalManager;

import app.model.municipalManager.MunicipalManagerIsAssignedTo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MunicipalManagerIsAssignedToRowMapper implements RowMapper<MunicipalManagerIsAssignedTo> {
    @Override
    public MunicipalManagerIsAssignedTo mapRow(ResultSet resultSet, int i) throws SQLException {
        MunicipalManagerIsAssignedTo municipalManagerIsAssignedTo = new MunicipalManagerIsAssignedTo();
        municipalManagerIsAssignedTo.setId(resultSet.getInt("id"));
        municipalManagerIsAssignedTo.setMunicipality(resultSet.getInt("municipality"));
        municipalManagerIsAssignedTo.setMunicipalManager(resultSet.getInt("municipal_manager"));
        municipalManagerIsAssignedTo.setPeriod(resultSet.getInt("period"));
        return municipalManagerIsAssignedTo;
    }
}
