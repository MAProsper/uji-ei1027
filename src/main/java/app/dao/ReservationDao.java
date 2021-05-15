package app.dao;

import app.dao.generic.Dao;
import app.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ReservationDao extends Dao<Reservation> {
    public List<Reservation> getByCitizen(int id) {
        return getByField("citizen", id);
    }

    public List<Map<String, Object>> getView() {
        String select = "R.id, code, name, date, TO_CHAR(P.period_start, 'HH:MI - ') || TO_CHAR(P.period_end, 'HH:MI') AS time, R.enter = R.exit AS active";
        String from = "Reservation R JOIN AreaPeriod P ON R.area_period = P.id JOIN Area A ON P.area = A.id";
        return jdbc.queryForList("SELECT " +
                " FROM ");
    }
}