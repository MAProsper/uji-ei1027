package app.controller.generic;

import app.util.SanaException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SanaControllerAdvice {
    @ExceptionHandler(SanaException.class)
    public ModelAndView handleClubException(SanaException e) {
        ModelAndView mav = new ModelAndView("error/exceptionError.html");
        mav.addObject("message", e.getMessage());
        return mav;
    }
}