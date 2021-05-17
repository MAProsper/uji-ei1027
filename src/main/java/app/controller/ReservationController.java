package app.controller;

import app.model.Reservation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
public class ReservationController extends app.controller.generic.Controller<Reservation> {
}
