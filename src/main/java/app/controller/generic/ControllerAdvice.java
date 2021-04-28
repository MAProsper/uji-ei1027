package app.controller.generic;

import app.ApplicationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ApplicationException.class)
    public ModelAndView handleException(ApplicationException e) {
        ModelAndView mav = new ModelAndView("error/exceptionError.html");
        mav.addObject("message", e.getMessage());
        return mav;
    }
}