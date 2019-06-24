package com.felix.exceptioninterceptor.handler;


import com.felix.exceptioninterceptor.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)
    public ModelAndView handleUserNotExistsException(UserNotExistException e) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("userException");
        return mv;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("500");
        return mv;
    }
}
