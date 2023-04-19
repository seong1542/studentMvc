package com.nhnacademy.studentmvc.controller;

import com.nhnacademy.studentmvc.domain.Student;
import com.nhnacademy.studentmvc.domain.StudentRegisterRequest;
import com.nhnacademy.studentmvc.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student/register")
public class StudentRegisterController implements BaseController{
    private final StudentRepository studentRepository;
    public StudentRegisterController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String studentRegisterForm(){
        return "register";
    }
    @PostMapping
    public ModelAndView registerUser(@ModelAttribute StudentRegisterRequest studentRequest){
        Student newStudent = new Student(studentRequest.getId(), studentRequest.getName(), studentRequest.getGender(), studentRequest.getAge());
        studentRepository.save(newStudent);
        ModelAndView modelAndView = new ModelAndView("studentInfo");
        modelAndView.addObject("student", newStudent);
        return modelAndView;
    }
}
