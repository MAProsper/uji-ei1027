package app.controller;

import app.controller.generic.Controller;
import app.model.Reservation;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/reservation")
public class ReservationController extends Controller<Reservation> {
}
