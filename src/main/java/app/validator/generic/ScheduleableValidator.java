package app.validator.generic;

import app.model.generic.Scheduleable;
import org.springframework.validation.Errors;

public class ScheduleableValidator<T extends Scheduleable> extends Validator<T> {
    public ScheduleableValidator(Class<T> cls) {
        super(cls);
    }

    @Override
    public void validate(T object, Errors errors) {
        super.validate(object, errors);
        if (object.getScheduleStart().compareTo(object.getScheduleEnd()) < 0) errors.rejectValue(ValidationMessage.SHEDULE_ORDER.getHuman(), ValidationMessage.SHEDULE_ORDER.getMachine());
        if (object.getPeriodStart().compareTo(object.getPeriodEnd()) < 0) errors.rejectValue(ValidationMessage.PERDIOD_ORDER.getHuman(), ValidationMessage.PERDIOD_ORDER.getMachine());
    }
}
