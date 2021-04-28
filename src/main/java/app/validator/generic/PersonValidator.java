package app.validator.generic;

import app.model.generic.Person;

public abstract class PersonValidator<T extends Person> extends SignableValidator<T> {
}
