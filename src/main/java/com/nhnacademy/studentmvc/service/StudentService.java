package com.nhnacademy.studentmvc.service;

import com.nhnacademy.studentmvc.domain.Student;
import com.nhnacademy.studentmvc.exception.DuplicateStudentException;
import com.nhnacademy.studentmvc.exception.StudentNotFoundException;
import com.nhnacademy.studentmvc.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student getStudent(String id){
        Student student =studentRepository.getStudentById(id);
        if(Objects.isNull(student)){
            throw new StudentNotFoundException("해당하는 학생이 없습니다.");
        }
        return student;
    }

    public void save(Student student){
        if(studentRepository.isExistById(student.getId())){
            throw new DuplicateStudentException();
        }
        studentRepository.save(student);
    }

    public void update(Student student){
        studentRepository.update(student);
    }
    public void delete(String id){
        Student student = studentRepository.getStudentById(id);
        if(Objects.isNull(student)){
            throw new StudentNotFoundException("없는 학생이니 지울 수 없어요");
        }
        studentRepository.delete(id);
    }

    public List<Student> getStudentList(){
        return studentRepository.getStudentList();
    }
}
