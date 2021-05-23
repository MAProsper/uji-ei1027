package app.controller;

import app.controller.generic.ScheduableController;
import app.model.Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service")
public class ServiceController extends ScheduableController<Service> {
}
