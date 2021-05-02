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
    public void test() {
        super.test();
        for (T person : getAll()) {
            person.setPassword(encryptor.encryptPassword(person.getPassword()));
            update(person);
        }
    }
}
