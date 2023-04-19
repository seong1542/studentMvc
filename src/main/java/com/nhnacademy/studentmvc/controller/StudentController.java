package com.nhnacademy.studentmvc.controller;

import com.nhnacademy.studentmvc.domain.Student;
import com.nhnacademy.studentmvc.domain.StudentUpdateRequest;
import com.nhnacademy.studentmvc.repository.StudentRepository;
import com.nhnacademy.studentmvc.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.attribute.standard.PresentationDirection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController implements BaseController {
    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @ModelAttribute("student")
    public Student getStudent(@PathVariable("studentId") String studentId){
        Student student = studentService.getStudent(studentId);
        if (Objects.isNull(student)) {
//            throw new StudentNotFoundException();
            System.out.println("학생없음");
        }
        return student;
    }
    @GetMapping("/view/{studentId}")
    public String getStudentInfo(@ModelAttribute Student student, Model model){
        model.addAttribute("student", student);
        return "studentInfo";
    }
    @GetMapping("/update/{studentId}")
    public String studentUpdateform(@ModelAttribute Student student, Model model){
        model.addAttribute("student", student);
        return "register";
    }

    @PostMapping("/update/{studentId}")
    public String getStudentupdate(@ModelAttribute Student student,
                                   @Valid @ModelAttribute StudentUpdateRequest request,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes,
                                   Model model){
        if(bindingResult.hasFieldErrors()){
            redirectAttributes.addFlashAttribute("message", "수정실패함");
        }
        student.setName(request.getName());
        student.setGender(request.getGender());
        student.setAge(request.getAge());
        studentService.update(student);
        model.addAttribute("student", student);
        return "redirect:/student/view/"+student.getId();
    }

    @PostMapping("/delete/{studentId}")
    public String delete(@ModelAttribute Student student){
        String id=student.getId();
        studentService.delete(id);
        return "redirect:/student/list";
    }
}
