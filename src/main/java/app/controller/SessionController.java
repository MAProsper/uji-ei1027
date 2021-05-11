package app.controller;

import app.model.Session;
import app.service.SessionService;
import app.validator.SessionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/session")
public class SessionController {
    @Autowired protected SessionValidator validator;
    @Autowired protected SessionService service;

    @RequestMapping("/add")
    public String add(org.springframework.ui.Model model) {
        model.addAttribute("object", new Session());
        return "session";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("object") Session object, BindingResult binding, HttpSession session) {
        validator.validate(object, binding);
        if (binding.hasErrors()) return "session";
        session.setAttribute("user", service.getPerson(object));
        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String delete(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
