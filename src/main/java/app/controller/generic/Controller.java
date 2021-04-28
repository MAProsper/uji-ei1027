package app.controller.generic;

import app.dao.generic.Dao;
import app.model.generic.Model;
import app.validator.generic.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public abstract class Controller<M extends Model, D extends Dao<M>, V extends Validator<M>> {
    @Autowired protected D dao;
    @Autowired protected V validator;

    @RequestMapping("/list")
    public String list(org.springframework.ui.Model model) {
        model.addAttribute("list", dao.getAll());
        return getView("list");
    }

    @RequestMapping("/add")
    public String add(org.springframework.ui.Model model) {
        model.addAttribute("model", dao.getReflect().newInstance());
        return getView("add");
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("model") M model, BindingResult binding) {
        return executePost("add", model, binding);
    }

    @RequestMapping("/update/{id}")
    public String update(org.springframework.ui.Model model, @PathVariable int id) {
        model.addAttribute("model", dao.getById(id));
        return getView("update");
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("model") M model, BindingResult binding) {
        return executePost("update", model, binding);
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        dao.deleteById(id);
        return "redirect:list";
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
        dao.executeUpdate(action, model);
        return getView("list");
    }

    /**
     * Obtiene la vista particular añadiendo el prefijo del modelo
     *
     * @param view nombre de la vista
     * @return ruta de la vista
     */
    protected String getView(String view) {
        return String.format("%s/%s", dao.getMapper().getTableName(), view).toLowerCase();
    }
}
