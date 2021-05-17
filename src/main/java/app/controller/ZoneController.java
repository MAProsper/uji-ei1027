package app.controller;

import app.controller.generic.PlaceController;
import app.model.Zone;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zone")
public class ZoneController extends PlaceController<Zone> {
}
