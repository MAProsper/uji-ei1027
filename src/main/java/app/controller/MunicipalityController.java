package app.controller;

import app.controller.generic.Controller;
import app.model.Municipality;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/municipality")
public class MunicipalityController extends Controller<Municipality> {
}
