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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
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
     * Obtener un modelo y vista
     *
     * @param model  modelo de la vista
     * @param object objeto referencia
     * @param data   obtener datos del objeto
     * @param view   vista actual
     * @return vista
     */
    protected <T> String model(Model model, T object, Function<T, Object> data, String view) {
        model.addAttribute("object", object).addAttribute("data", data.apply(object));
        return getView(view);
    }

    /**
     * Obtener datos de los objetos de un listado
     *
     * @param objects objetos referencia
     * @param empty   objeto vacio
     * @return datos del objeto
     */
    protected List<Map<String, Object>> data(List<M> objects, M empty) {
        return objects.isEmpty() ? Collections.singletonList(service.data(empty)) : objects.stream().map(service::data).collect(Collectors.toList());
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
     * @param data      obtener datos del objeto
     * @return vista o error
     */
    protected <T> String object(HttpSession session, Integer arg, BiFunction<HttpSession, Integer, Boolean> validator, String view, Model model, BiFunction<HttpSession, Integer, T> factory, Function<T, Object> data) {
        return setup(session, arg, validator, view).orElseGet(() -> model(model, factory.apply(session, arg), data, view));
    }

    /**
     * Peticion de procesado de objectos
     *
     * @param session   sesion
     * @param arg       argumento opcional
     * @param validator validador de peticion
     * @param view      vista actual
     * @param process   procesado del objeto
     * @param redirect  redirecion de resultado
     * @return vista o error
     */
    protected String process(HttpSession session, Integer arg, BiFunction<HttpSession, Integer, Boolean> validator, String view, Supplier<Optional<String>> process, BiFunction<HttpSession, Integer, String> redirect) {
        return setup(session, arg, validator, view).orElseGet(() -> process.get().orElseGet(() -> getRedirect(redirect.apply(session, arg))));
    }

    /**
     * Peticion de procesado de objectos (formulario)
     *
     * @param session   sesion
     * @param arg       argumento opcional
     * @param validator validador de peticion
     * @param view      vista actual
     * @param model     modelo de la vista
     * @param object    objeto del formulario
     * @param binding   error en el objeto
     * @param process   procesado del objeto
     * @param redirect  redirecion de resultado
     * @return vista o error
     */
    protected String process(HttpSession session, Integer arg, BiFunction<HttpSession, Integer, Boolean> validator, String view, Model model, M object, BindingResult binding, TriConsumer<HttpSession, Integer, M> process, BiFunction<HttpSession, Integer, M> factory, BiFunction<HttpSession, Integer, String> redirect) {
        return process(session, arg, validator, view, () -> {
            reflect.merge(factory.apply(session, arg), object);
            this.validator.validate(object, binding);
            if (binding.hasErrors()) return Optional.of(model(model, object, service::data, view));
            process.accept(session, arg, object);
            return Optional.empty();
        }, redirect);
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
        return process(session, arg, validator::add, "add", model, object, binding, service::addProcess, service::addObject, service::redirectParent);
    }

    @RequestMapping({"/update", "/update/{arg}"})
    public String update(HttpSession session, @PathVariable(required = false) Integer arg, Model model) {
        return object(session, arg, validator::update, "update", model, service::updateObject, service::data);
    }

    @RequestMapping(path = {"/update", "/update/{arg}"}, method = RequestMethod.POST)
    public String updateProcess(HttpSession session, @PathVariable(required = false) Integer arg, Model model, @ModelAttribute("object") M object, BindingResult binding) {
        return process(session, arg, validator::update, "update", model, object, binding, service::updateProcess, service::updateObject, service::redirectSelf);
    }

    @RequestMapping({"/delete", "/delete/{arg}"})
    public String delete(HttpSession session, @PathVariable(required = false) Integer arg) {
        return process(session, arg, validator::delete, "delete", () -> {
            service.deleteProcess(session, arg);
            return Optional.empty();
        }, service::redirectSelf);
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
     * @param view vista destino
     * @return URL destino
     */
    protected String getRedirect(String view) {
        return String.format("redirect:%s", view);
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
