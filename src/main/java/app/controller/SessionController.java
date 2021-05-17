package app.controller;

import app.model.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SessionController extends app.controller.generic.Controller<Session> {
    @RequestMapping("/")
    public String index(HttpSession session) {
        super.setReferrer(session, "/");
        return "index";
    }

    @Override
    protected void setReferrer(HttpSession session, String referrer) {
    }
}
