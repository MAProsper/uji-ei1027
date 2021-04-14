package dao.municipalManager.municipalManagerIsAssignedTo;

import model.municipalManager.MunicipalManagerIsAssignetTo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MunicipalManagerIsAssignedToRowMapper implements RowMapper<MunicipalManagerIsAssignetTo> {
    @Override
    public MunicipalManagerIsAssignetTo mapRow(ResultSet resultSet, int i) throws SQLException {
        MunicipalManagerIsAssignetTo municipalManagerIsAssignetTo = new MunicipalManagerIsAssignetTo();
        municipalManagerIsAssignetTo.setId(resultSet.getInt("id"));
        municipalManagerIsAssignetTo.setMunicipality(resultSet.getInt("municipality"));
        municipalManagerIsAssignetTo.setMunicipalManager(resultSet.getInt("municipal_manager"));
        municipalManagerIsAssignetTo.setPeriod(resultSet.getInt("period"));
        return municipalManagerIsAssignetTo;
    }
}
