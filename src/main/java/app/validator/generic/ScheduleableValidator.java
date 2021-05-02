package app.validator.generic;

import app.model.generic.Scheduleable;
import org.springframework.validation.Errors;

public abstract class ScheduleableValidator<T extends Scheduleable> extends Validator<T> {
    @Override
    public void validate(T object, Errors errors) {
        super.validate(object, errors);

        if (object.getScheduleStart().compareTo(object.getScheduleEnd()) > 0)
            errors.rejectValue("scheduleEnd", "validador", "La fecha de fin deber ser posterior a la de incio");

        if (object.getPeriodStart().compareTo(object.getPeriodEnd()) > 0)
            errors.rejectValue("periodEnd", "validador", "La hora de fin deber ser posterior a la de incio");
    }
}
