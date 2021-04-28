package app.validator.generic;

import app.model.generic.Scheduleable;
import org.springframework.validation.Errors;

public abstract class ScheduleableValidator<T extends Scheduleable> extends Validator<T> {
    @Override
    public void validate(T object, Errors errors) {
        super.validate(object, errors);

        if (object.getScheduleStart().compareTo(object.getScheduleEnd()) < 0)
            errors.rejectValue("La fecha de fin deber ser posterior a la de incio", "scheduleOrder");

        if (object.getPeriodStart().compareTo(object.getPeriodEnd()) < 0)
            errors.rejectValue("La hora de fin deber ser posterior a la de incio", "periodOrder");
    }
}
