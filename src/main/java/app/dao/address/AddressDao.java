package app.dao.address;

import app.dao.Dao;
import app.model.address.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class AddressDao extends Dao<Address> {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired Logger logger;

    public void add(Address address) {
        jdbcTemplate.update("INSERT INTO Address VALUES(?, ?, ?, ?, ?, ?)",
                address.getId(), address.getCountry(), address.getCity(), address.getStreet(), address.getNumber(), address.getOther());
    }

    public void update(Address address) {
        jdbcTemplate.update("UPDATE Address SET id =?, country =?, city =?, street =?, number =?, other =?",
                address.getId(), address.getCountry(), address.getCity(), address.getStreet(), address.getNumber(), address.getOther());
    }
}
