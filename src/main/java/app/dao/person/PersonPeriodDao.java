package app.dao.person;

import app.dao.Dao;
import app.model.person.Person;
import app.model.person.PersonPeriod;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonPeriodDao extends Dao<PersonPeriod> {
    public PersonPeriodDao() {
        super(PersonPeriod.class);
    }

    public List<PersonPeriod> getByPerson(Person person) {
        try {
            return jdbc.query("SELECT * FROM PersonPeriod WHERE person =?", mapper, person.getId());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public PersonPeriod getByDate(LocalDateTime date) {
        try {
            return jdbc.queryForObject("SELECT * FROM PersonPeriod JOIN Period ON PersonPeriod.period = Period.id WHERE Period.start <= =? AND COALESCE(Period.end, =?) >= =?", mapper, date);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
