package app.dao.area;

import app.dao.Dao;
import app.model.area.Area;
import app.model.area.AreaCharacteristic;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AreaCharacteristicDao extends Dao<AreaCharacteristic> {
    public AreaCharacteristicDao() {
        super(AreaCharacteristic.class);
    }

    public List<AreaCharacteristic> getByArea(Area area) {
        try {
            return jdbc.query("SELECT * FROM AreaCharacteristic WHERE area =?", mapper, area.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
