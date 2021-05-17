package app.controller;

import app.controller.generic.PlaceController;
import app.model.Area;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/area")
public class AreaController extends PlaceController<Area> {
}
