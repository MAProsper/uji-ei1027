package app.controller;

import app.controller.generic.ControllerV2;
import app.model.Zone;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zone")
public class ZoneController extends ControllerV2<Zone> {
}
