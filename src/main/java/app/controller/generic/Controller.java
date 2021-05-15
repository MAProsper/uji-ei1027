package app.controller.generic;

import app.model.generic.Model;
import app.service.generic.Service;
import app.util.StringUtil;
import app.validator.generic.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class Controller<M extends Model> {
    @Autowired protected Service<M> service;
    @Autowired protected Validator<M> validator;

    private Optional<String> requestSetup(HttpSession session, String referrer, boolean valid) {
        setReferrer(session, referrer);
        return valid ? Optional.empty() : Optional.of("redirect:/session/add");
    }

    protected String listModel(org.springframework.ui.Model model, List<M> objects, Map<String,String> data) {
        model.addAttribute("objectsData", objects.stream().map(service::listObjectData).collect(Collectors.toList()));
        model.addAttribute("requestData", data);
        model.addAttribute("objects", objects);
        return getView("list");
    }

    @RequestMapping("/list")
    public String list(HttpSession session, org.springframework.ui.Model model) {
        Optional<String> error = requestSetup(session, getReferrer("list"), validator.list(session));
        return error.orElseGet(() -> listModel(model, service.listObjects(session), service.listRequestData(session)));
    }

    @RequestMapping("/list/{arg}")
    public String list(HttpSession session, org.springframework.ui.Model model, @PathVariable int arg) {
        Optional<String> error = requestSetup(session, getReferrer("list", arg), validator.list(session, arg));
        return error.orElseGet(() -> listModel(model, service.listObjects(session, arg), service.listRequestData(session, arg)));
    }

    private String addModel(org.springframework.ui.Model model, M object, Map<String, String> data) {
        model.addAttribute("object", object);
        model.addAttribute("requestData", data);
        return getView("add");
    }

    @RequestMapping("/add")
    public String add(HttpSession session, org.springframework.ui.Model model) {
        Optional<String> error = requestSetup(session, getReferrer("add"), validator.add(session));
        return error.orElseGet(() -> addModel(model, service.addObject(session), service.addRequestData(session)));
    }

    @RequestMapping("/add/{arg}")
    public String add(HttpSession session, org.springframework.ui.Model model, @PathVariable int arg) {
        Optional<String> error = requestSetup(session, getReferrer("add", arg), validator.add(session, arg));
        return error.orElseGet(() -> addModel(model, service.addObject(session, arg), service.addRequestData(session, arg)));
    }

    protected Optional<String> addProcess(M object, BindingResult binding) {
        validator.validate(object, binding);
        return binding.hasErrors() ? Optional.of(getView("add")) : Optional.empty();
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addProcess(HttpSession session, @ModelAttribute M object, BindingResult binding) {
        Optional<String> error = requestSetup(session, getReferrer("add"), validator.add(session));
        if (error.isPresent()) return error.get();
        error = addProcess(object, binding);
        if (error.isPresent()) return error.get();
        service.addProcess(object, session);
        return getRedirect(service.addRedirect(session));
    }

    @RequestMapping(path = "/add/{arg}", method = RequestMethod.POST)
    public String addProcess(HttpSession session, @ModelAttribute M object, @PathVariable int arg, BindingResult binding) {
        Optional<String> error = requestSetup(session, getReferrer("add", arg), validator.add(session, arg));
        if (error.isPresent()) return error.get();
        error = addProcess(object, binding);
        if (error.isPresent()) return error.get();
        service.addProcess(object, session, arg);
        return getRedirect(service.addRedirect(session, arg));
    }

    protected String updateModel(org.springframework.ui.Model model, M object, Map<String, String> data) {
        model.addAttribute("object", object);
        model.addAttribute("requestData", data);
        return getView("update");
    }

    @RequestMapping("/update")
    public String update(HttpSession session, org.springframework.ui.Model model) {
        Optional<String> error = requestSetup(session, getReferrer("update"), validator.update(session));
        return error.orElseGet(() -> updateModel(model, service.updateObject(session), service.updateRequestData(session)));
    }

    @RequestMapping("/update/{arg}")
    public String update(HttpSession session, org.springframework.ui.Model model, @PathVariable int arg) {
        Optional<String> error = requestSetup(session, getReferrer("update", arg), validator.update(session, arg));
        return error.orElseGet(() -> updateModel(model, service.updateObject(session, arg), service.updateRequestData(session, arg)));
    }

    protected Optional<String> updateProcess(M object, BindingResult binding) {
        validator.validate(object, binding);
        return binding.hasErrors() ? Optional.of(getView("update")) : Optional.empty();
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public String updateProcess(HttpSession session, @ModelAttribute M object, BindingResult binding) {
        Optional<String> error = requestSetup(session, getReferrer("update"), validator.update(session));
        if (error.isPresent()) return error.get();
        error = updateProcess(object, binding);
        if (error.isPresent()) return error.get();
        service.updateProcess(object, session);
        return getRedirect(service.updateRedirect(session));
    }

    @RequestMapping(path = "/update/{arg}", method = RequestMethod.POST)
    public String updateProcess(HttpSession session, @ModelAttribute M object, @PathVariable int arg, BindingResult binding) {
        Optional<String> error = requestSetup(session, getReferrer("update", arg), validator.update(session, arg));
        if (error.isPresent()) return error.get();
        error = updateProcess(object, binding);
        if (error.isPresent()) return error.get();
        service.updateProcess(object, session, arg);
        return getRedirect(service.updateRedirect(session, arg));
    }

    @RequestMapping("/delete")
    public String delete(HttpSession session, org.springframework.ui.Model model) {
        Optional<String> error = requestSetup(session, getReferrer("delete"), validator.delete(session));
        if (error.isPresent()) return error.get();
        service.deleteProcess(session);
        return getRedirect(service.deleteRedirect(session));
    }

    @RequestMapping("/delete/{arg}")
    public String delete(HttpSession session, org.springframework.ui.Model model, @PathVariable int arg) {
        Optional<String> error = requestSetup(session, getReferrer("delete", arg), validator.delete(session, arg));
        if (error.isPresent()) return error.get();
        service.deleteProcess(session, arg);
        return getRedirect(service.deleteRedirect(session, arg));
    }

    protected String getView(String view) {
        return String.format("%s/%s", StringUtil.toPackageCase(service.getName()), view);
    }

    protected String getRedirect(String view) {
        return String.format("redirect:%s", view);
    }

    protected String getReferrer(String view) {
        return String.format("/%s/%s", StringUtil.toUrlCase(service.getName()), view);
    }

    protected String getReferrer(String view, int arg) {
        return String.format("%s/%d", getReferrer(view), arg);
    }

    protected void setReferrer(HttpSession session, String referrer) {
        session.setAttribute("referrer", referrer);
    }
}
