package app.validator;

import app.dao.AreaPeriodDao;
import app.dao.ReservationDao;
import app.model.Citizen;
import app.model.Reservation;
import app.validator.generic.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ReservationValidator extends Validator<Reservation> {
    @Autowired AreaPeriodDao areaPeriodDao;
    @Autowired ReservationDao reservationDao;

    @Override
    public boolean list(HttpSession session, Integer arg) {
        return arg != null ? forbidden() : ifPerson(session, Citizen.class); // Importante: reservas de una parte
    }

    @Override
    public boolean add(HttpSession session, Integer arg) {
        return arg == null || areaPeriodDao.getById(arg) == null ? forbidden() : ifPerson(session, Citizen.class);
    }

    @Override
    public boolean update(HttpSession session, Integer arg) {
        return arg == null || reservationDao.getById(arg) == null ? forbidden() : ifPerson(session);
    }

    @Override
    public boolean delete(HttpSession session, Integer arg) {
        return arg == null ? forbidden() : update(session, arg);
    }
}
