package app.controller;

import app.controller.generic.Controller;
import app.model.Session;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/session")
public class SessionController extends Controller<Session> {
}
