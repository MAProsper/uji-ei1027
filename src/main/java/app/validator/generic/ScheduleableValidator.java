package app.validator.generic;

import app.model.generic.Scheduleable;

public abstract class ScheduleableValidator<T extends Scheduleable> extends Validator<T> {
    @Override
    public void object(T object, FieldErrors errors) {
        super.object(object, errors);

        if (object.getScheduleStart().isBefore(object.getScheduleEnd()))
            errors.accept("scheduleEnd", "La fecha de fin deber ser posterior a la de incio");

        if (object.getPeriodStart().isBefore(object.getPeriodEnd()))
            errors.accept("periodEnd", "La hora de fin deber ser posterior a la de incio");
    }
}
