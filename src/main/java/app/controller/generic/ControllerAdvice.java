package app.controller.generic;

import app.util.SanaException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(SanaException.class)
    public ModelAndView handleException(SanaException e) {
        ModelAndView mav = new ModelAndView("error/exceptionError.html");
        mav.addObject("message", e.getMessage());
        return mav;
    }
}