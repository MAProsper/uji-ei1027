package app.dao.address;

import app.model.address.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class AddressDao {
    private JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(Address address) {
        jdbcTemplate.update("INSERT INTO Address VALUES(?, ?, ?, ?, ?, ?)",
                address.getId(), address.getCountry(), address.getCity(), address.getStreet(), address.getNumber(), address.getOther());
    }

    public void update(Address address) {
        jdbcTemplate.update("UPDATE Address SET id =?, country =?, city =?, street =?, number =?, other =?",
                address.getId(), address.getCountry(), address.getCity(), address.getStreet(), address.getNumber(), address.getOther());
    }

    public void delete(Address address) {
        jdbcTemplate.update("DELETE FROM Address WHERE id =?",
                address.getId());
    }

    public List<Address> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM Address", new AddressRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public Address getById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Address WHERE id =?", new AddressRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void test() {
        logger.info(getClass().getName() + ".getAll() = " + getAll());
    }
}
