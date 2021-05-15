package app.controller;

import app.controller.generic.Controller;
import app.model.Area;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/area")
public class AreaController extends Controller<Area> {
}
