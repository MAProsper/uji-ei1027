package app.dao.generic;

import app.model.generic.Scheduleable;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public abstract class ScheduleableDao<T extends Scheduleable> extends Dao<T> {
    @Override
    public void delete(int id) {
        T object = getById(id);
        LocalDate today = LocalDate.now();
        object.setScheduleStart(Collections.min(List.of(object.getScheduleStart(), today.minusDays(1))));
        object.setScheduleEnd(today);
        update(object);
    }
}
