package app.validator.generic;

import app.model.generic.Model;

import javax.servlet.http.HttpSession;

public abstract class SessionableValidator<T extends Model> extends Validator<T> {
    @Override
    public boolean validate(HttpSession session) {
        return session.getAttribute("user") != null;
    }
}
