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

    protected String list(org.springframework.ui.Model model, List<M> objects) {
        model.addAttribute("objects", objects);
        model.addAttribute("objectsData", objects.stream().map(service::listObjectData).collect(Collectors.toList()));
        return getView("list");
    }
    @RequestMapping("/list")
    public String list(HttpSession session, org.springframework.ui.Model model) {
        if (!validator.list(session)) return "redirect:/session/add";
        model.addAttribute("requestData", service.listRequestData(session));
        return list(model, service.listObjects(session));
    }
    @RequestMapping("/list/{arg}")
    public String list(HttpSession session, org.springframework.ui.Model model, @PathVariable int arg) {
        if (!validator.list(session, arg)) return "redirect:/session/add";
        model.addAttribute("requestData", service.listRequestData(session, arg));
        return list(model, service.listObjects(session, arg));
    }

    protected String add(org.springframework.ui.Model model, M object) {
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

    protected Optional<String> addProcess(M object, BindingResult binding) {
        validator.data(object, binding);
        if (binding.hasErrors()) return Optional.of(getView("add"));
        service.getDao().add(object);
        return Optional.empty();
    }
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addProcess(HttpSession session, @ModelAttribute M object, BindingResult binding) {
        if (validator.add(session)) return "redirect:/session/add";
        return addProcess(object, binding).orElse(getRedirect());
    }
    @RequestMapping(path = "/add/{arg}", method = RequestMethod.POST)
    public String addProcess(HttpSession session, @ModelAttribute M object, @PathVariable int arg, BindingResult binding) {
        if (validator.add(session, arg)) return "redirect:/session/add";
        return addProcess(object, binding).orElse(getRedirect(arg));
    }

    protected String update(org.springframework.ui.Model model, M object) {
        model.addAttribute("object", object);
        return getView("add");
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

    protected Optional<String> updateProcess(M object, BindingResult binding) {
        validator.data(object, binding);
        if (binding.hasErrors()) return Optional.of(getView("update"));
        service.getDao().update(object);
        return Optional.empty();
    }
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public String updateProcess(HttpSession session, @ModelAttribute M object, BindingResult binding) {
        if (validator.update(session)) return "redirect:/session/add";
        return updateProcess(object, binding).orElse(getRedirect());
    }
    @RequestMapping(path = "/update/{arg}", method = RequestMethod.POST)
    public String updateProcess(HttpSession session, @ModelAttribute M object, @PathVariable int arg, BindingResult binding) {
        if (validator.update(session, arg)) return "redirect:/session/add";
        return updateProcess(object, binding).orElse(getRedirect(arg));
    }

    @RequestMapping("/delete")
    public String delete(HttpSession session, org.springframework.ui.Model model) {
        if (validator.delete(session)) return "redirect:/session/add";
        service.deleteObject(session);
        return getRedirect();
    }
    @RequestMapping("/delete/{arg}")
    public String delete(HttpSession session, org.springframework.ui.Model model, @PathVariable int arg) {
        if (validator.delete(session, arg)) return "redirect:/session/add";
        service.deleteObject(session, arg);
        return getRedirect(arg);
    }

    protected String getView(String view) {
        return String.format("%s/%s", StringUtil.toPackageCase(service.getName()), view);
    }

    protected String getRedirect() {
        return String.format("redirect:/%s/list", StringUtil.toUrlCase(service.getName()));
    }

    protected String getRedirect(int arg) {
        return String.format("redirect:/%s/list/%s", StringUtil.toUrlCase(service.getName()), arg);
    }
}
