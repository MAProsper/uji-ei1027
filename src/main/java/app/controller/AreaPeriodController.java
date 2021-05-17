package app.controller;

import app.controller.generic.ScheduableController;
import app.model.AreaPeriod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/area-period")
public class AreaPeriodController extends ScheduableController<AreaPeriod> {
}
