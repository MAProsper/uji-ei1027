package app.controller;

import app.controller.generic.Controller;
import app.dao.ServiceTypeDao;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class Test extends Controller<ServiceTypeDao> {
    public Test() {
        super();
    }

    @Override
    public String add(Model model) {
        return "null";
    }

    @Override
    public String update(Model model) {
        return "null";
    }

    @Override
    public String delete() {
        return "null";
    }

    public void test() {
        logger.info(dao.getAll().toString());
    }
}
