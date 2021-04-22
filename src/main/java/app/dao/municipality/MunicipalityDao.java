package app.dao.municipality;

import app.dao.Dao;
import app.model.municipality.Municipality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class MunicipalityDao extends Dao<Municipality> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(Municipality municipality) {
        jdbcTemplate.update("INSERT INTO Municipality VALUES(?)",
                municipality.getPlace());
    }

    public void update(Municipality municipality) {
        jdbcTemplate.update("UPDATE Municipality SET id =?",
                municipality.getPlace());
    }
}
