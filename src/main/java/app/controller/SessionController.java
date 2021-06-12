package app.controller;

import app.model.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SessionController extends app.controller.generic.Controller<Session> {
    @RequestMapping("/")
    public String index(HttpSession session, Model model) {
        super.setReferrer(session, "/");
        return object(session, null, validator::add, "index", model, service::addObject, service::addObject, service::data);
    }

    @Override
    protected void setReferrer(HttpSession session, String referrer) {
    }
}
