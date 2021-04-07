package model.citizen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class CitizenDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(Citizen citizen) {
        jdbcTemplate.update("INSERT INTO Citizen VALUES(?)", citizen.getPerson());
    }

    public void delete(Citizen citizen) {
        jdbcTemplate.update("DELETE FROM Citizen  WHERE person = ?", citizen.getPerson());
    }

    public void update(Citizen citizen) {
        jdbcTemplate.update("UPDATE Citizen SET person =?", citizen.getPerson());
    }

    public List<Citizen> get() {
        try {
            return jdbcTemplate.query("SELECT * FROM Citizen", new CitizenRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public Citizen get(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Citizen WHERE person =?", new CitizenRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
