package app.controller;

import app.controller.generic.ControllerV2;
import app.controller.generic.SessionableController;
import app.model.Reservation;
import app.model.generic.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
public class ReservationController extends ControllerV2<Reservation> {
}
