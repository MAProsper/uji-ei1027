package app.service.generic;

import app.model.generic.Scheduleable;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public abstract class ScheduableService<T extends Scheduleable> extends Service<T> {
    @Override
    public T addObject(HttpSession session, Integer arg) {
        T object = super.addObject(session, arg);
        object.setScheduleStart(LocalDate.now());
        return object;
    }
}
