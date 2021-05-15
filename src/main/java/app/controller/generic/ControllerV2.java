package app.controller.generic;

import app.model.generic.Model;
import app.service.generic.ServiceV2;
import app.util.StringUtil;
import app.validator.generic.ValidatorV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ControllerV2<M extends Model> {
    @Autowired protected ServiceV2<M> service;
    @Autowired protected ValidatorV2<M> validator;

    private String listProcess(org.springframework.ui.Model model, List<M> objects) {
        model.addAttribute("objects", objects);
        model.addAttribute("objectsData", objects.stream().map(service::listObjectData).collect(Collectors.toList()));
        return getView("list");
    }
    @RequestMapping("/list")
    public String list(HttpSession session, org.springframework.ui.Model model) {
        if (!validator.list(session)) return "redirect:/session/add";
        model.addAttribute("requestData", service.listRequestData(session));
        return listProcess(model, service.listObjects(session));
    }
    @RequestMapping("/list/{arg}")
    public String list(HttpSession session, org.springframework.ui.Model model, @PathVariable int arg) {
        if (!validator.list(session, arg)) return "redirect:/session/add";
        model.addAttribute("requestData", service.listRequestData(session, arg));
        return listProcess(model, service.listObjects(session, arg));
    }

    private String add(org.springframework.ui.Model model, M object) {
        model.addAttribute("object", object);
        return getView("add");
    }
    @RequestMapping("/add")
    public String add(HttpSession session, org.springframework.ui.Model model) {
        if (!validator.add(session)) return "redirect:/session/add";
        model.addAttribute("requestData", service.addRequestData(session));
        return add(model, service.addObject(session));
    }
    @RequestMapping("/add/{arg}")
    public String add(HttpSession session, org.springframework.ui.Model model, @PathVariable int arg) {
        if (!validator.add(session, arg)) return "redirect:/session/add";
        model.addAttribute("requestData", service.addRequestData(session, arg));
        return add(model, service.addObject(session, arg));
    }

    private Optional<String> addProcess(M object, BindingResult binding) {
        validator.validate(object, binding);
        return binding.hasErrors() ? Optional.of(getView("add")) : Optional.empty();
    }
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addProcess(HttpSession session, @ModelAttribute M object, BindingResult binding) {
        if (!validator.add(session)) return "redirect:/session/add";
        Optional<String> error = addProcess(object, binding);
        if (error.isPresent()) return error.get();
        service.addProcess(object, session);
        return getRedirect(service.addRedirect(session));
    }
    @RequestMapping(path = "/add/{arg}", method = RequestMethod.POST)
    public String addProcess(HttpSession session, @ModelAttribute M object, @PathVariable int arg, BindingResult binding) {
        if (!validator.add(session, arg)) return "redirect:/session/add";
        Optional<String> error = addProcess(object, binding);
        if (error.isPresent()) return error.get();
        service.addProcess(object, session, arg);
        return getRedirect(service.addRedirect(session, arg));
    }

    protected String update(org.springframework.ui.Model model, M object) {
        model.addAttribute("object", object);
        return getView("update");
    }
    @RequestMapping("/update")
    public String update(HttpSession session, org.springframework.ui.Model model) {
        if (!validator.add(session)) return "redirect:/session/add";
        model.addAttribute("requestData", service.updateRequestData(session));
        return update(model, service.updateObject(session));
    }
    @RequestMapping("/update/{arg}")
    public String update(HttpSession session, org.springframework.ui.Model model, @PathVariable int arg) {
        if (!validator.add(session, arg)) return "redirect:/session/add";
        model.addAttribute("requestData", service.updateRequestData(session, arg));
        return update(model, service.updateObject(session, arg));
    }

    private Optional<String> updateProcess(M object, BindingResult binding) {
        validator.validate(object, binding);
        return binding.hasErrors() ? Optional.of(getView("update")) : Optional.empty();
    }
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public String updateProcess(HttpSession session, @ModelAttribute M object, BindingResult binding) {
        if (!validator.update(session)) return "redirect:/session/add";
        Optional<String> error = updateProcess(object, binding);
        if (error.isPresent()) return error.get();
        service.updateProcess(object, session);
        return getRedirect(service.updateRedirect(session));
    }
    @RequestMapping(path = "/update/{arg}", method = RequestMethod.POST)
    public String updateProcess(HttpSession session, @ModelAttribute M object, @PathVariable int arg, BindingResult binding) {
        if (!validator.update(session, arg)) return "redirect:/session/add";
        Optional<String> error = updateProcess(object, binding);
        if (error.isPresent()) return error.get();
        service.updateProcess(object, session, arg);
        return getRedirect(service.updateRedirect(session, arg));
    }

    @RequestMapping("/delete")
    public String delete(HttpSession session, org.springframework.ui.Model model) {
        if (!validator.delete(session)) return "redirect:/session/add";
        service.deleteProcess(session);
        return getRedirect(service.deleteRedirect(session));
    }
    @RequestMapping("/delete/{arg}")
    public String delete(HttpSession session, org.springframework.ui.Model model, @PathVariable int arg) {
        if (!validator.delete(session, arg)) return "redirect:/session/add";
        service.deleteProcess(session, arg);
        return getRedirect(service.deleteRedirect(session, arg));
    }

    protected String getView(String view) {
        return String.format("%s/%s", StringUtil.toPackageCase(service.getName()), view);
    }

    protected String getRedirect(String view) {
        return String.format("redirect:%s", view);
    }
}
