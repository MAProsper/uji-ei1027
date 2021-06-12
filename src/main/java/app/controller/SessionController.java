package app.controller;

import app.model.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Controller
public class SessionController extends app.controller.generic.Controller<Session> {
    @RequestMapping("/")
    public String index(HttpSession session, Model model) {
        super.setReferrer(session, "/");
        return object(session, null, validator::add, "index", model, service::addObject, service::data, service::addObject);
    }

    @Override
    protected void setReferrer(HttpSession session, String referrer) {
    }
}
