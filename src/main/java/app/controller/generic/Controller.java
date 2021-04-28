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
    public String add(@ModelAttribute("model") M model, BindingResult bindingResult) {
        validator.validate(model, bindingResult);
        if (bindingResult.hasErrors()) return getView("add");

        try {
            dao.add(model);
        } catch (DataIntegrityViolationException e) {
            throw new SanaException("error de integridad", e);
        }

        return getView("list");
    }

    @RequestMapping("/update/{id}")
    public String update(org.springframework.ui.Model model, @PathVariable int id) {
        model.addAttribute("model", dao.getById(id));
        return getView("update");
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("model") M model, BindingResult bindingResult) {
        validator.validate(model, bindingResult);
        if (bindingResult.hasErrors()) return getView("add");

        try {
            dao.update(model);
        } catch (DataIntegrityViolationException e) {
            throw new SanaException("error de integridad", e);
        }

        return getView("list");
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        dao.deleteById(id);
        return "redirect:list";
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
