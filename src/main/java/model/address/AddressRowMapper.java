package model.address;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements RowMapper<Address> {
    @Override
    public Address mapRow(ResultSet rs, int i) throws SQLException {
        Address address = new Address();
        address.setId(rs.getInt("id"));
        address.setCountry(rs.getString("county"));
        address.setCity(rs.getString("city"));
        address.setStreet(rs.getString("street"));
        address.setNumber(rs.getInt("number"));
        address.setOther(rs.getString("other"));
        return address;
    }
}
