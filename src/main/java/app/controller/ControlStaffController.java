package app.controller;

import app.controller.generic.PersonController;
import app.model.ControlStaff;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/control-staff")
public class ControlStaffController extends PersonController<ControlStaff> {
}
