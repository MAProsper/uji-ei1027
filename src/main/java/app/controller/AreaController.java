package app.controller;

import app.controller.generic.ControllerV2;
import app.model.Area;
import app.model.Municipality;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/area")
public class AreaController extends ControllerV2<Area> {
}
