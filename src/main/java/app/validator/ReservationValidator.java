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
        if (arg != null) return forbidden();  // Importante: reservas de una parte
        return ifPerson(session, Citizen.class);
    }

    @Override
    public boolean add(HttpSession session, Integer arg) {
        if (arg == null || areaPeriodDao.getById(arg) == null) return forbidden();
        return ifPerson(session, Citizen.class);
    }

    @Override
    public boolean update(HttpSession session, Integer arg) {
        if (arg == null || reservationDao.getById(arg) == null) return forbidden();
        return ifPerson(session);
    }

    @Override
    public boolean delete(HttpSession session, Integer arg) {
        if (arg == null) return forbidden();
        return update(session, arg);
    }
}
