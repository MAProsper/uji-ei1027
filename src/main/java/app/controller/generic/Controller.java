package app.controller.generic;

import app.dao.generic.Dao;
import app.model.generic.Model;
import app.service.generic.Service;
import app.validator.generic.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Controller<M extends Model> {
    @Autowired protected Service<M> service;
    @Autowired protected Validator<M> validator;

    @RequestMapping("/list")
    public String list(HttpSession session, org.springframework.ui.Model model) {
        List<M> objects = service.getAll(session);
        model.addAttribute("objects", objects);
        model.addAttribute("data", objects.stream().map(service::otherData).collect(Collectors.toList()));
        return getView("list");
    }

    @RequestMapping("/add")
    public String add(HttpSession session, org.springframework.ui.Model model) {
        model.addAttribute("object", getDao().getReflect().newInstance());
        return getView("add");
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String add(HttpSession session, @ModelAttribute M object, BindingResult binding) {
        return executePost("add", object, binding);
    }

    @RequestMapping("/update/{id}")
    public String update(HttpSession session, org.springframework.ui.Model model, @PathVariable int id) {
        model.addAttribute("object", getDao().getById(id));
        return getView("update");
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
    public String update(HttpSession session, @ModelAttribute M object, BindingResult binding) {
        return executePost("update", object, binding);
    }

    @RequestMapping("/delete/{id}")
    public String delete(HttpSession session, org.springframework.ui.Model model, @PathVariable int id) {
        getDao().delete(id);
        return "redirect:../list";
    }

    /**
     * Gestiona respuesta POST validando el modelo y actualizando la base datos
     *
     * @param action  accion ha gestionar
     * @param model   objeto referencia
     * @param binding binding del objeto
     * @return sigiente vista
     */
    protected String executePost(String action, M model, BindingResult binding) {
        validator.validate(model, binding);
        if (binding.hasErrors()) return getView(action);
        getDao().executeByName(action, model);
        return "redirect:../list";
    }

    /**
     * Obtiene la vista particular añadiendo el prefijo del modelo
     *
     * @param view nombre de la vista
     * @return ruta de la vista
     */
    protected String getView(String view) {
        return String.format("%s/%s", getDao().getMapper().getTableName(), view).toLowerCase(); //TODO: Revisar el formato de las vistas en disco
    }

    protected Dao<M> getDao() {
        return service.getDao();
    }
}
