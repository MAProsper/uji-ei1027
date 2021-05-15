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

    private Optional<String> requestProcess(M object, BindingResult binding, String view) {
        validator.validate(object, binding);
        return binding.hasErrors() ? Optional.of(getView(view)) : Optional.empty();
    }

    protected String requestModel(org.springframework.ui.Model model, M object, Map<String, String> data, String view) {
        model.addAttribute("object", object).addAttribute("requestData", data);
        return getView(view);
    }

    protected String requestModel(org.springframework.ui.Model model, List<M> objects, Map<String,String> data) {
        model.addAttribute("objects", objects).addAttribute("requestData", data).addAttribute("objectsData", objects.stream().map(service::listObjectData).collect(Collectors.toList()));
        return getView("list");
    }

    @RequestMapping("/list")
    public String list(HttpSession session, org.springframework.ui.Model model) {
        return requestSetup(session, getReferrer("list"), validator.list(session)).orElseGet(() -> requestModel(model, service.listObjects(session), service.listRequestData(session)));
    }

    @RequestMapping("/list/{arg}")
    public String list(HttpSession session, org.springframework.ui.Model model, @PathVariable int arg) {
        return requestSetup(session, getReferrer("list", arg), validator.list(session, arg)).orElseGet(() -> requestModel(model, service.listObjects(session, arg), service.listRequestData(session, arg)));
    }

    @RequestMapping("/add")
    public String add(HttpSession session, org.springframework.ui.Model model) {
        return requestSetup(session, getReferrer("add"), validator.add(session)).orElseGet(() -> requestModel(model, service.addObject(session), service.addRequestData(session), "add"));
    }

    @RequestMapping("/add/{arg}")
    public String add(HttpSession session, org.springframework.ui.Model model, @PathVariable int arg) {
        return requestSetup(session, getReferrer("add", arg), validator.add(session, arg)).orElseGet(() -> requestModel(model, service.addObject(session, arg), service.addRequestData(session, arg), "add"));
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addProcess(HttpSession session, @ModelAttribute M object, BindingResult binding) {
        return requestSetup(session, getReferrer("add"), validator.add(session)).or(() -> requestProcess(object, binding, "add")).orElseGet(() -> getRedirect(service.addProcess(object, session)));
    }

    @RequestMapping(path = "/add/{arg}", method = RequestMethod.POST)
    public String addProcess(HttpSession session, @ModelAttribute M object, @PathVariable int arg, BindingResult binding) {
        return requestSetup(session, getReferrer("add", arg), validator.add(session)).or(() -> requestProcess(object, binding, "add")).orElseGet(() -> getRedirect(service.addProcess(object, session, arg)));
    }

    @RequestMapping("/update")
    public String update(HttpSession session, org.springframework.ui.Model model) {
        return requestSetup(session, getReferrer("update"), validator.update(session)).orElseGet(() -> requestModel(model, service.updateObject(session), service.updateRequestData(session), "update"));
    }

    @RequestMapping("/update/{arg}")
    public String update(HttpSession session, org.springframework.ui.Model model, @PathVariable int arg) {
        return requestSetup(session, getReferrer("update", arg), validator.update(session, arg)).orElseGet(() -> requestModel(model, service.updateObject(session, arg), service.updateRequestData(session, arg), "update"));
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public String updateProcess(HttpSession session, @ModelAttribute M object, BindingResult binding) {
        return requestSetup(session, getReferrer("update"), validator.update(session)).or(() -> requestProcess(object, binding, "update")).orElseGet(() -> getRedirect(service.updateProcess(object, session)));
    }

    @RequestMapping(path = "/update/{arg}", method = RequestMethod.POST)
    public String updateProcess(HttpSession session, @ModelAttribute M object, @PathVariable int arg, BindingResult binding) {
        return requestSetup(session, getReferrer("update", arg), validator.update(session, arg)).or(() -> requestProcess(object, binding, "update")).orElseGet(() -> getRedirect(service.updateProcess(object, session, arg)));
    }

    @RequestMapping("/delete")
    public String delete(HttpSession session, org.springframework.ui.Model model) {
        return requestSetup(session, getReferrer("delete"), validator.delete(session)).orElseGet(() -> getRedirect(service.deleteProcess(session)));
    }

    @RequestMapping("/delete/{arg}")
    public String delete(HttpSession session, org.springframework.ui.Model model, @PathVariable int arg) {
        return requestSetup(session, getReferrer("delete", arg), validator.delete(session, arg)).orElseGet(() -> getRedirect(service.deleteProcess(session, arg)));
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
