package app.validator.generic;

import app.model.generic.Scheduleable;

public abstract class ScheduleableValidator<T extends Scheduleable> extends Validator<T> {
    @Override
    public void object(T object, FieldError error) {
        super.object(object, error);

        if (object.getScheduleStart().compareTo(object.getScheduleEnd()) > 0)
            error.accept("scheduleEnd", "La fecha de fin deber ser posterior a la de incio");

        if (object.getPeriodStart().compareTo(object.getPeriodEnd()) > 0)
            error.accept("periodEnd", "La hora de fin deber ser posterior a la de incio");
    }
}
