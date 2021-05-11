package app.controller;

import app.controller.generic.Controller;
import app.controller.generic.SessionableController;
import app.model.Reservation;
import app.model.generic.Person;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/reservation")
public class ReservationController extends SessionableController<Reservation> {
}
