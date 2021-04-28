package app.controller.generic;

import app.dao.generic.Dao;
import app.dao.generic.Mapper;
import app.model.generic.Model;
import app.util.Reflect;
import app.util.SanaException;
import app.validator.generic.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;
import java.util.logging.Logger;

public abstract class Controller<M extends Model, D extends Dao<M>, V extends Validator<M>> {
    @Autowired protected D dao;
    @Autowired protected V validator;
    @Autowired protected Logger logger;

    @RequestMapping("/list")
    public String list(org.springframework.ui.Model model) {
        model.addAttribute("list", dao.getAll());
        return getView("list");
    }

    @RequestMapping("/add")
    public String add(org.springframework.ui.Model model) {
        model.addAttribute("model", getReflect().newInstance());
        return getView("add");
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("model") M model, BindingResult binding) {
        return executeUpdate("add", model, binding);
    }

    @RequestMapping("/update/{id}")
    public String update(org.springframework.ui.Model model, @PathVariable int id) {
        model.addAttribute("model", dao.getById(id));
        return getView("update");
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("model") M model, BindingResult binding) {
        return executeUpdate("update", model, binding);
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        dao.deleteById(id);
        return "redirect:list";
    }

    protected String executeUpdate(String action, M model, BindingResult binding) {
        validator.validate(model, binding);
        if (binding.hasErrors()) return getView(action);
        executeUpdate(action, model);
        return getView("list");
    }

    protected void executeUpdate(String action, M model) {
        Class<M> cls = getReflect().getReflectClass();
        try {
            dao.getClass().getMethod(action, cls).invoke(dao, model);
        } catch (DataIntegrityViolationException e) {
            throw new SanaException("error de integridad", e);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new SanaException(e.getMessage(), e);
        }
    }

    protected Mapper<M> getMapper() {
        return dao.getMapper();
    }

    protected Reflect<M> getReflect() {
        return getMapper().getReflect();
    }

    protected String getView(String view) {
        return String.format("%s/%s", getMapper().getTableName(), view);
    }
}
