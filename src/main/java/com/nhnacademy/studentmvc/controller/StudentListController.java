package com.nhnacademy.studentmvc.controller;

import com.nhnacademy.studentmvc.domain.Student;
import com.nhnacademy.studentmvc.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentListController implements BaseController{
    private final StudentRepository studentRepository;
    public StudentListController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @GetMapping("/list")
    public String getStudentList(Model model){
        List<Student> students = studentRepository.getStudentList();
        log.error("1");
        for(Student student:students){
            System.out.println(student.getName());
            log.error("2");
        }
        if(Objects.isNull(students)){
//            throw new StudentNotFoundException();
            System.out.println("학생정보가 로드되지 않았습니다.");
            log.error("ㄴ2");
            return "index";
        }
        model.addAttribute("students",students);
        return "studentList";
    }
}
