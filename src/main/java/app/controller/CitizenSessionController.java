package app.controller;

import app.controller.generic.SessionController;
import app.model.Citizen;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/session/citizen")
public class CitizenSessionController extends SessionController<Citizen> {
}
