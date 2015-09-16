package ar.edu.unq.tac.market.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.edu.unq.tac.market.service.exception.MarketRuntimeException;


@ControllerAdvice
public class ExceptionController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);
    
    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public Map<String,String> errorResponse(IllegalStateException ex, HttpServletResponse response){
        LOGGER.info("Handling InvocationTargetException");
        Throwable cause = ex.getCause();
        Map<String,String> errorMap = new HashMap<String,String>();
            if(cause instanceof InvocationTargetException){
                InvocationTargetException castCause = (InvocationTargetException) cause;
                Throwable target = castCause.getTargetException();
                if(target instanceof MarketRuntimeException){
                    MarketRuntimeException marketEx = (MarketRuntimeException) target;
                    errorMap.put("msg", marketEx.getMessage());
                    errorMap.put("key", marketEx.getKeyError());
                    String params = marketEx.getJsonParams();
                    if(params != null){
                        errorMap.put("params", params);
                    }
                    response.setStatus(HttpStatus.BAD_REQUEST.value());
                    return errorMap;
                }
            }
        errorMap.put("msg", ex.getMessage());
        errorMap.put("key", "unexpectedError");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return errorMap;
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String,String> errorResponse(Exception ex, HttpServletResponse response){
        LOGGER.info("Handling Exception");
        LOGGER.info(ex.getClass().getCanonicalName());
        Map<String,String> errorMap = new HashMap<String,String>();
        errorMap.put("msg", ex.getMessage());
        errorMap.put("key", "unexpectedError");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return errorMap;
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Map<String,String> errorResponse(ConstraintViolationException ex, HttpServletResponse response){
        LOGGER.debug("Handling MarketRuntimeException");
        Map<String,String> errorMap = new HashMap<String,String>();
        errorMap.put("msg", ex.getMessage());
        errorMap.put("key", "validationErrors");
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<String> validationErrors = new LinkedList<String>();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
           validationErrors.add(constraintViolation.getMessage());
        }
        String imploded = String.join(",", validationErrors);
        errorMap.put("validationErrors", imploded);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return errorMap;
    }
    
    @ExceptionHandler(MarketRuntimeException.class)
    @ResponseBody
    public Map<String,String> errorResponse(MarketRuntimeException ex, HttpServletResponse response){
        LOGGER.info("Handling MarketRuntimeException");
        Map<String,String> errorMap = new HashMap<String,String>();
        errorMap.put("msg", ex.getMessage());
        errorMap.put("key", ex.getKeyError());
        String params = ex.getJsonParams();
        if(params != null){
            errorMap.put("params", params);
        }
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return errorMap;
    }
    
    

}
