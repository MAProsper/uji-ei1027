package app.controller;

import app.controller.generic.Controller;
import app.dao.AreaPeriodDao;
import app.dao.ReservationDao;
import app.model.Reservation;
import app.validator.ReservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
@RequestMapping("/reservation")
public class ReservationController extends Controller<Reservation, ReservationDao, ReservationValidator> {
    @Autowired AreaPeriodDao areaPeriodDao;

    @Override
    public String list(Model model) {
        model.addAttribute("period", dao.getAll().stream().map(reservation -> areaPeriodDao.getById(reservation.getAreaPeriod())).collect(Collectors.toList()));
        return super.list(model);
    }
}
