package app.dao.generic;

import app.model.generic.Scheduleable;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public abstract class ScheduleableDao<T extends Scheduleable> extends Dao<T> {
    @Override
    public void add(T object) {
        LocalDate today = LocalDate.now();
        object.setScheduleStart(Collections.max(List.of(object.getScheduleStart(), today)));
        super.add(object);
    }

    @Override
    public void delete(int id) {
        T object = getById(id);
        LocalDate prev = LocalDate.now().minusDays(1);
        object.setScheduleStart(Collections.min(List.of(object.getScheduleStart(), prev)));
        object.setScheduleEnd(prev);
        update(object);
    }
}
