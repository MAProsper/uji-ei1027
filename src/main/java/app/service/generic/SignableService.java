package app.service.generic;

import app.model.generic.Signable;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public abstract class SignableService<T extends Signable> extends Service<T> {
    @Override
    public T addObject(HttpSession session, Integer arg) {
        T object = super.addObject(session, arg);
        object.setSingUp(LocalDateTime.now());
        return object;
    }
}
