package app.controller;

import app.controller.generic.Controller;
import app.model.Zone;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/zone")
public class ZoneController extends Controller<Zone> {
}
