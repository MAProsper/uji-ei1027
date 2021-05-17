package app.validator.generic;

import app.model.generic.Scheduleable;

public abstract class ScheduleableValidator<T extends Scheduleable> extends Validator<T> {
    @Override
    public void object(T object, FieldError errors) {
        super.object(object, errors);

        if (object.getScheduleStart().compareTo(object.getScheduleEnd()) > 0)
            errors.accept("scheduleEnd", "La fecha de fin deber ser posterior a la de incio");

        if (object.getPeriodStart().compareTo(object.getPeriodEnd()) > 0)
            errors.accept("periodEnd", "La hora de fin deber ser posterior a la de incio");
    }
}
