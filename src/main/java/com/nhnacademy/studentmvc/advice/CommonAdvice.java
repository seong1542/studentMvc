package com.nhnacademy.studentmvc.advice;

import com.nhnacademy.studentmvc.exception.DuplicateStudentException;
import com.nhnacademy.studentmvc.exception.StudentNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Slf4j
public class CommonAdvice {
    @InitBinder
    void initBinder(WebDataBinder binder){
        binder.initDirectFieldAccess();
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String studentNotFound(StudentNotFoundException studentNotFoundException){
        return "studentNotFound";
    }

    @ExceptionHandler(DuplicateStudentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String studentDuplicated(DuplicateStudentException duplicateStudentException){
        return "duplicatedStudent";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String pageNotFound(){
        return "404";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalServletError(Exception e, Model model){
        model.addAttribute("exceptionMessage", e.getMessage());
        return "500";
    }

}
