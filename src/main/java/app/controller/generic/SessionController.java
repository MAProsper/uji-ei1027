package app.controller.generic;

import app.dao.generic.PersonDao;
import app.model.generic.Person;
import app.validator.generic.SessionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

public abstract class SessionController<T extends Person> {
    @Autowired protected SessionValidator<T> validator;
    @Autowired protected PersonDao<T> dao;

    @RequestMapping("add")
    public String add(org.springframework.ui.Model model) {
        model.addAttribute("object", dao.getReflect().newInstance());
        return "session";
    }

    @RequestMapping(path = "add", method = RequestMethod.POST)
    public String process(@ModelAttribute("object") T object, BindingResult binding, HttpSession session) {
        validator.validate(object, binding);
        if (binding.hasErrors()) return "session";
        session.setAttribute("user", object);
        return "redirect:/";
    }

    @RequestMapping("delete")
    public String delete(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
