package app.service.generic;

import app.model.generic.Person;

public abstract class PersonService<T extends Person> extends SignableService<T> {
}
