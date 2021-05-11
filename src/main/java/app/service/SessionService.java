package app.service;

import app.dao.generic.PersonDao;
import app.model.Session;
import app.model.generic.Person;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SessionService {
    @Autowired protected PasswordEncryptor encryptor;
    @Autowired protected Map<String,PersonDao<?>> personDaos;

    @Autowired
    protected void setPersonDaos(List<PersonDao<?>> personDaos) {
        this.personDaos = personDaos.stream().collect(Collectors.toMap(dao -> dao.getMapper().getTableName(), Function.identity()));
    }

    public Person getPerson(Session session) {
            PersonDao<?> dao = personDaos.get(session.getType());
            Person person = dao.getByIdentification(session.getIdentification());
            return person != null  && encryptor.checkPassword(session.getPassword(), person.getPassword()) ? person : null;
    }

    public Set<String> getTypes() {
        return personDaos.keySet();
    }
}
