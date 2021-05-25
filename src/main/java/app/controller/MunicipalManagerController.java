package app.controller;

import app.controller.generic.PersonController;
import app.model.MunicipalManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/municipal-manager")
public class MunicipalManagerController extends PersonController<MunicipalManager> {
}
