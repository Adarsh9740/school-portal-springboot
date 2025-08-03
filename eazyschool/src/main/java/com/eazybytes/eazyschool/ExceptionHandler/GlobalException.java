package com.eazybytes.eazyschool.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    public ModelAndView HandleExceptions(Exception e){
        log.info("Error - "+ e.toString());
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("errormsg",e.getMessage());

        return  modelAndView;
    }
}
