package dao.municipality;

import model.municipality.Municipality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MunicipalityDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(Municipality municipality) {
        jdbcTemplate.update("INSERT INTO Municipality VALUES(?)",
                municipality.getPlace());
    }

    public void update(Municipality municipality) {
        jdbcTemplate.update("UPDATE Municipality SET id =?",
                municipality.getPlace());
    }

    public void delete(Municipality municipality) {
        jdbcTemplate.update("DELETE FROM Municipality WHERE id =?",
                municipality.getPlace());
    }

    public List<Municipality> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM Municipality", new MunicipalityRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public Municipality getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Municipality WHERE place =?", new MunicipalityRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}