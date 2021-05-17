package app.controller.generic;

import app.service.generic.Service;
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
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public abstract class Controller<M extends app.model.generic.Model> {
    @Autowired protected Service<M> service;
    @Autowired protected Validator<M> validator;

    /**
     * Verificar si la peticion es valida
     *
     * @param session   sesion
     * @param arg       argumento opcional
     * @param validator validador
     * @param view      vista actual
     * @return posible error
     */
    protected Optional<String> requestSetup(HttpSession session, Integer arg, BiFunction<HttpSession, Integer, Boolean> validator, String view) {
        setReferrer(session, getReferrer(view, arg));
        return validator.apply(session, arg) ? Optional.empty() : Optional.of("redirect:/add");
    }

    /**
     * Añadir a las vistas de modificacion valores basicoas
     *
     * @param session sesion
     * @param arg     argumento opcional
     * @param model   modelo de la vista
     * @param key     nombre del objeto en la vista
     * @param value   objecto referencia
     */
    protected void requestData(HttpSession session, Integer arg, Model model, String key, Object value) {
        model.addAttribute("requestData", service.requestData(session, arg)).addAttribute(key, value);
    }

    /**
     * Peticion de presentacion de formulario
     *
     * @param session   sesion
     * @param arg       argumento opcional
     * @param validator validador de peticion
     * @param view      vista actual
     * @param model     modelo de la vista
     * @param factory   obtener objeto para formulario
     * @return vista o error
     */
    protected String requestObject(HttpSession session, Integer arg, BiFunction<HttpSession, Integer, Boolean> validator, String view, Model model, BiFunction<HttpSession, Integer, Object> factory) {
        return requestSetup(session, arg, validator, view).orElseGet(() -> {
            requestData(session, arg, model, "object", factory.apply(session, arg));
            return getView(view);
        });
    }

    /**
     * Peticion de procesado de formulario
     *
     * @param session   sesion
     * @param arg       argumento opcional
     * @param validator validador de peticion
     * @param view      vista actual
     * @param object    objeto referencia
     * @param binding   error en el objeto
     * @param process   procesado del objeto
     * @return vista o error
     */
    protected String requestProcess(HttpSession session, Integer arg, BiFunction<HttpSession, Integer, Boolean> validator, String view, M object, BindingResult binding, TriConsumer<HttpSession, Integer, M> process) {
        return requestSetup(session, arg, validator, view).orElseGet(() -> {
            service.requestProcess(session, arg, object);
            this.validator.validate(object, binding);
            if (binding.hasErrors()) return getView(view);
            process.accept(session, arg, object);
            return getRedirect(session, arg);
        });
    }

    @RequestMapping({"/list", "/list/{arg}"})
    public String list(HttpSession session, @PathVariable(required = false) Integer arg, Model model) {
        return requestSetup(session, arg, validator::list, "list").orElseGet(() -> {
            List<M> objects = service.listObjects(session, arg);
            requestData(session, arg, model, "objects", objects);
            model.addAttribute("objectsData", objects.stream().map(service::listObjectData).collect(Collectors.toList()));
            return getView("list");
        });
    }

    @RequestMapping({"/delete", "/delete/{arg}"})
    public String delete(HttpSession session, @PathVariable(required = false) Integer arg) {
        return requestSetup(session, arg, validator::delete, "delete").orElseGet(() -> {
            service.deleteProcess(session, arg);
            return getRedirect(session, arg);
        });
    }

    @RequestMapping({"/add", "/add/{arg}"})
    public String add(HttpSession session, @PathVariable(required = false) Integer arg, Model model) {
        return requestObject(session, arg, validator::add, "add", model, service::addObject);
    }

    @RequestMapping({"/update", "/update/{arg}"})
    public String update(HttpSession session, @PathVariable(required = false) Integer arg, Model model) {
        return requestObject(session, arg, validator::update, "update", model, service::updateObject);
    }

    @RequestMapping(path = {"/add", "/add/{arg}"}, method = RequestMethod.POST)
    public String addProcess(HttpSession session, @PathVariable(required = false) Integer arg, @ModelAttribute M object, BindingResult binding) {
        return requestProcess(session, arg, validator::add, "add", object, binding, service::addProcess);
    }

    @RequestMapping(path = {"/update", "/update/{arg}"}, method = RequestMethod.POST)
    public String updateProcess(HttpSession session, @PathVariable(required = false) Integer arg, @ModelAttribute M object, BindingResult binding) {
        return requestProcess(session, arg, validator::update, "update", object, binding, service::updateProcess);
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
