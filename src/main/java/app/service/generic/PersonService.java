package app.service.generic;

import app.ApplicationException;
import app.dao.generic.Mapper;
import app.dao.generic.PersonDao;
import app.model.generic.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PersonService {
    protected Map<String, PersonDao<?>> personDaos;

    @Autowired
    protected void setPersonDaos(List<PersonDao<?>> personDaos) {
        this.personDaos = personDaos.stream().collect(Collectors.toMap(dao -> dao.getMapper().getTableName(), Function.identity()));
    }

    public PersonDao<?> getDaoByName(String name) {
        PersonDao<?> personDao = personDaos.get(name);
        if (personDao == null) throw new ApplicationException("tipo de usuario invalido");
        return personDao;
    }

    public PersonDao<?> getDaoByInstance(Person object) {
        return getDaoByName(new Mapper<>(object.getClass()).getTableName());
    }
}
