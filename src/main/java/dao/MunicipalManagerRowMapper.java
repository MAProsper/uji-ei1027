package dao;

import model.MunicipalManager;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MunicipalManagerRowMapper implements RowMapper<MunicipalManager> {
    @Override
    public MunicipalManager mapRow(ResultSet resultSet, int i) throws SQLException {
        MunicipalManager municipalManager = new MunicipalManager();
        municipalManager.setPerson(resultSet.getInt("person"));
        return municipalManager;
    }
}
