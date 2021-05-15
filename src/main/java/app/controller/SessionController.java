package app.controller;

import app.controller.generic.Controller;
import app.model.Session;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
@RequestMapping("/session")
public class SessionController extends Controller<Session> {
    @Override
    protected void setReferrer(HttpSession session, String referrer) {
    }
}
