package model.municipalManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MunicipalManagerDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(MunicipalManager municipalManager) {
        jdbcTemplate.update("INSERT INTO MunicipalManager VALUES(?)", municipalManager.getPerson());
    }

    public void delete(MunicipalManager municipalManager) {
        jdbcTemplate.update("DELETE FROM MunicipalManager  WHERE id = ?", municipalManager.getPerson());
    }

    public void update(MunicipalManager municipalManager) {
        jdbcTemplate.update("UPDATE MunicipalManager SET person =?", municipalManager.getPerson());
    }

    public List<MunicipalManager> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM MunicipalManager", new MunicipalManagerRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}
