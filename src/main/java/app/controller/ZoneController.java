package app.controller;

import app.controller.generic.PlaceController;
import app.model.Zone;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/zone")
public class ZoneController extends PlaceController<Zone> {
}
