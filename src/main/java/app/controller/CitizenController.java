package app.controller;

import app.controller.generic.PersonController;
import app.model.Citizen;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/citizen")
public class CitizenController extends PersonController<Citizen> {
}
