package app.controller.generic;

import app.dao.generic.Dao;
import app.model.generic.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@org.springframework.stereotype.Service
public abstract class Controller<D extends Dao<? extends Model>> {

    //TODO: Hacer
    @Autowired protected D dao;
    @Autowired protected Logger logger;

    public Controller() {
    }

    @RequestMapping("/add")
    public abstract String add(org.springframework.ui.Model model);

    @RequestMapping("/list")
    public String list(org.springframework.ui.Model model){
        return "/list";
    }

    @RequestMapping("/update")
    public abstract String update(org.springframework.ui.Model model);

    @RequestMapping("/delete/{id}")
    public abstract String delete();

}
