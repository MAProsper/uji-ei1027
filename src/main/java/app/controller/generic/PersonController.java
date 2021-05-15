package app.controller.generic;

import app.model.generic.Person;

public abstract class PersonController<T extends Person> extends SignableController<T> {
}
