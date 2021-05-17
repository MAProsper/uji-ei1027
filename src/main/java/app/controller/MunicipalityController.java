package app.controller;

import app.controller.generic.PlaceController;
import app.model.Municipality;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/municipality")
public class MunicipalityController extends PlaceController<Municipality> {
}
