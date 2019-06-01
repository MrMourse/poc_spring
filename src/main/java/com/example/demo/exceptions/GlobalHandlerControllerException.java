package com.example.demo.exceptions;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(basePackages = {"com.example.demo.DemoApplication"} )//Spring 3.2 And Above
public class GlobalHandlerControllerException extends ResponseEntityExceptionHandler{

    @ModelAttribute //la variable "technicalError" pourra être exploité n'importe où dans l'application
    public void globalAttributes(Model model) {
        model.addAttribute("technicalError", "Une erreur technique est survenue !");
    }

    @ExceptionHandler(TechnicalErrorException.class)
    public ModelAndView technicalErrorException(Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception.getMessage());
        mav.setViewName("error");
        return mav;
    }
    @ExceptionHandler(Exception.class)//toutes les autres erreurs non gérées sont interceptées ici
    public ResponseEntity<List<String>> unknowError(HttpServletRequest req, Exception ex) throws Throwable{
        Throwable cause = ex.getCause();
        if (!(cause instanceof RollbackException)) throw cause;
        if (!(cause.getCause() instanceof ConstraintViolationException)) throw cause.getCause();
        ConstraintViolationException validationException = (ConstraintViolationException) cause.getCause();
        List<String> messages = validationException.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.toList());
        return new ResponseEntity<>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessResourceException.class)
    public ResponseEntity<BusinessResourceExceptionResponse> resourceNotFound(HttpServletRequest req, BusinessResourceException ex) {
        BusinessResourceExceptionResponse response = new BusinessResourceExceptionResponse();
        response.setStatus(ex.getStatus());
        response.setErrorCode(ex.getErrorCode());
        response.setErrorMessage(ex.getMessage());
        response.setRequestURL(req.getRequestURL().toString());
        return new ResponseEntity<>(response, ex.getStatus());
    }

}