package app.validator.generic;

import app.model.generic.Scheduleable;

import java.time.LocalDate;

public abstract class ScheduleableValidator<T extends Scheduleable> extends Validator<T> {
    @Override
    public void object(T object, FieldErrors errors) {
        super.object(object, errors);

        if (object.getScheduleStart() == null)
            errors.accept("scheduleStart", "Formato de fecha de inicio inválida");
        else if (object.getId() == 0 && object.getScheduleStart().isBefore(LocalDate.now()))
            errors.accept("scheduleStart", "La fecha de inicio deber ser posterior o igual a hoy");
        else if (object.getScheduleEnd() != null && (object.getScheduleStart().isAfter(object.getScheduleEnd()) || object.getScheduleEnd().isBefore(LocalDate.now())))
            errors.accept("scheduleEnd", "La fecha de fin deber ser posterior o igual a la de inicio y a hoy");

        if (object.getPeriodStart() == null)
            errors.accept("periodStart", "Formato de hora de inicio inválida");
        else if (object.getPeriodEnd() == null)
            errors.accept("periodEnd", "Formato de hora de fin inválida");
        else if (object.getPeriodStart().compareTo(object.getPeriodEnd()) >= 0)
            errors.accept("periodEnd", "La hora de fin deber ser posterior a la de inicio");
    }
}
