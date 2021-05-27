package app.dao.generic;

import app.model.generic.Person;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PersonDao<T extends Person> extends SignableDao<T> {
    @Autowired protected PasswordEncryptor encryptor;

    public T getByIdentification(String identification) {
        return getFirst(getByField("identification", identification));
    }

    @Override
    public void add(T object) {
        object.setPassword(encryptor.encryptPassword(object.getPassword()));
        super.add(object);
    }

    @Override
    public void update(T object) {
        object.setPassword(object.getPassword().isBlank() ? getById(object.getId()).getPassword() : encryptor.encryptPassword(object.getPassword()));
        super.update(object);
    }
}
