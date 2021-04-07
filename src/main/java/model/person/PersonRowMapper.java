package model.person;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setIdentification(resultSet.getString("identification"));
        person.setName(resultSet.getString("name"));
        person.setUsername(resultSet.getString("username"));
        person.setPassword(resultSet.getString("password"));
        return person;
    }
}
