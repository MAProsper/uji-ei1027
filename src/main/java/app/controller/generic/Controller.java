package app.controller.generic;

import app.service.generic.Service;
import app.util.Parametrized;
import app.util.Reflect;
import app.util.StringUtil;
import app.validator.generic.Validator;
import org.apache.logging.log4j.util.TriConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class Controller<M extends app.model.generic.Model> extends Parametrized<M> {
    @Autowired protected Service<M> service;
    @Autowired protected Validator<M> validator;
    protected Reflect<M> reflect = new Reflect<>(getParametrizedType());

    /**
     * Verificar si la peticion es valida
     *
     * @param session   sesion
     * @param arg       argumento opcional
     * @param validator validador
     * @param view      vista actual
     * @return posible error
     */
    protected Optional<String> setup(HttpSession session, Integer arg, BiFunction<HttpSession, Integer, Boolean> validator, String view) {
        setReferrer(session, getReferrer(view, arg));
        return validator.apply(session, arg) ? Optional.empty() : Optional.of("redirect:/add");
    }

    /**
     * Peticion de presentacion de objectos
     *
     * @param session   sesion
     * @param arg       argumento opcional
     * @param validator validador de peticion
     * @param view      vista actual
     * @param model     modelo de la vista
     * @param factory   obtener objeto para formulario
     * @return vista o error
     */
    protected <T> String object(HttpSession session, Integer arg, BiFunction<HttpSession, Integer, Boolean> validator, String view, Model model, BiFunction<HttpSession, Integer, T> factory, Function<T, Object> data) {
        return setup(session, arg, validator, view).orElseGet(() -> {
            T object = factory.apply(session, arg);
            model.addAttribute("object", object).addAttribute("data", data.apply(object));
            return getView(view);
        });
    }

    protected List<Map<String, Object>> data(List<M> objects, M empty) {
        return objects.isEmpty() ? List.of(service.data(empty)) : objects.stream().map(service::data).collect(Collectors.toList());
    }

    /**
     * Peticion de procesado de objectos
     *
     * @param session   sesion
     * @param arg       argumento opcional
     * @param validator validador de peticion
     * @param view      vista actual
     * @param object    objeto referencia
     * @param binding   error en el objeto
     * @param process   procesado del objeto
     * @param model
     * @return vista o error
     */
    protected String process(HttpSession session, Integer arg, BiFunction<HttpSession, Integer, Boolean> validator, String view, M object, BindingResult binding, TriConsumer<HttpSession, Integer, M> process, BiFunction<HttpSession, Integer, M> factory, Model model) {
        return setup(session, arg, validator, view).orElseGet(() -> {
            reflect.merge(factory.apply(session, arg), object);
            this.validator.validate(object, binding);
            model.addAttribute("object", object).addAttribute("data", service.data(object));
            if (binding.hasErrors()) return getView(view);
            process.accept(session, arg, object);
            return getRedirect(session, arg);
        });
    }

    @RequestMapping({"/list", "/list/{arg}"})
    public String list(HttpSession session, @PathVariable(required = false) Integer arg, Model model) {
        return object(session, arg, validator::list, "list", model, service::listObjects, l -> data(l, service.addObject(session, arg)));
    }

    @RequestMapping({"/add", "/add/{arg}"})
    public String add(HttpSession session, @PathVariable(required = false) Integer arg, Model model) {
        return object(session, arg, validator::add, "add", model, service::addObject, service::data);
    }

    @RequestMapping(path = {"/add", "/add/{arg}"}, method = RequestMethod.POST)
    public String addProcess(HttpSession session, @PathVariable(required = false) Integer arg, Model model, @ModelAttribute("object") M object, BindingResult binding) {
        return process(session, arg, validator::add, "add", object, binding, service::addProcess, service::addObject, model);
    }

    @RequestMapping({"/update", "/update/{arg}"})
    public String update(HttpSession session, @PathVariable(required = false) Integer arg, Model model) {
        return object(session, arg, validator::update, "update", model, service::updateObject, service::data);
    }

    @RequestMapping(path = {"/update", "/update/{arg}"}, method = RequestMethod.POST)
    public String updateProcess(HttpSession session, @PathVariable(required = false) Integer arg, Model model, @ModelAttribute("object") M object, BindingResult binding) {
        return process(session, arg, validator::update, "update", object, binding, service::updateProcess, service::updateObject, model);
    }

    @RequestMapping({"/delete", "/delete/{arg}"})
    public String delete(HttpSession session, @PathVariable(required = false) Integer arg) {
        return setup(session, arg, validator::delete, "delete").orElseGet(() -> {
            service.deleteProcess(session, arg);
            return getRedirect(session, arg);
        });
    }

    /**
     * Obtener ruta a la vista basado en el modelo
     *
     * @param view nombre de vista
     * @return ruta a vista
     */
    protected String getView(String view) {
        return String.format("%s/%s", StringUtil.toPackageCase(service.getName()), view);
    }

    /**
     * Redirecionar a una URL
     *
     * @param session sesion
     * @param arg     argumento opcional
     * @return URL destino
     */
    protected String getRedirect(HttpSession session, Integer arg) {
        return String.format("redirect:%s", service.getRedirect(session, arg));
    }

    /**
     * Obtener URL actual
     *
     * @param view vista actual
     * @param arg  argumento opcional
     * @return URL
     */
    protected String getReferrer(String view, Integer arg) {
        if (arg != null) view = String.format("%s/%d", view, arg);
        return String.format("/%s/%s", StringUtil.toUrlCase(service.getName()), view);
    }

    /**
     * Guardar la URL como la ultima visitada
     *
     * @param session  sesion
     * @param referrer URL actual
     */
    protected void setReferrer(HttpSession session, String referrer) {
        session.setAttribute("referrer", referrer);
    }
}
