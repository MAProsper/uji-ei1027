package app.dao.generic;

import app.model.generic.Person;

public abstract class PersonDao<T extends Person> extends Dao<T> {
}
