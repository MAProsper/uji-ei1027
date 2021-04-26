package app.controlador;

import app.dao.generic.Dao;
import app.util.Reflect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public abstract class Controller<T> {

    //TODO: Hacer
    protected Reflect<T> reflect;
    protected Dao<T> dao;
    private String name;

    public Controller(Class<T> cls) {
        this.reflect = new Reflect<>(cls);
        this.dao = new Dao<T>(cls);
        this.name = this.reflect.getReflectClass().getSimpleName().toLowerCase();
    }

    @Autowired
    public void setDao(Dao<T> dao) {
        this.dao=dao;
    }

    @RequestMapping("/add")
    public abstract String add(Model model);

    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute(this.name+"s", this.dao.getAll());
        return name+"/list";
    }

    @RequestMapping("/update")
    public abstract String update(Model model);

    @RequestMapping("/delete/{id}")
    public abstract String delete();

}
