package app.controller;

import app.controller.generic.ControllerV2;
import app.model.Session;
import app.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/session")
public class SessionController extends ControllerV2<Session> {
}
