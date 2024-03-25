package com.buildingHelper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@SuppressWarnings("serial")
@ControllerAdvice
public class Common_exception{
    @ExceptionHandler(Exception.class)
    private ModelAndView handleOtherExceptions(Exception e) {
    	e.printStackTrace();
        ModelAndView modelAndView = new ModelAndView();
        // error.jsp로 이동합니다.
        modelAndView.setViewName("error");
        return modelAndView;
    }
    
    @ExceptionHandler(NullPointerException.class)
    private ModelAndView handleNullPointerException(NullPointerException e) {
    	e.printStackTrace();
        ModelAndView modelAndView = new ModelAndView();
        // error.jsp로 이동합니다.
        modelAndView.setViewName("error");
        return modelAndView;
    }
    

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ModelAndView notFoundPage(NoHandlerFoundException e) {
        ModelAndView modelAndView = new ModelAndView();
        // error.jsp로 이동합니다.
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
