package com.nhnacademy.studentmvc.config;

import com.nhnacademy.studentmvc.Base;
import com.nhnacademy.studentmvc.domain.Gender;
import com.nhnacademy.studentmvc.domain.Student;
import com.nhnacademy.studentmvc.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Random;

@Configuration
@ComponentScan(basePackageClasses = Base.class, excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {
    @Bean
    public StudentRepository studentRepository(){
        Random rand = new Random();
//        String realPath = getClass().getResource("/data/student.json").getPath();
        StudentRepository studentRepository = null;
//        try {
//            studentRepository = new JsonStudentRepository(realPath);
            studentRepository =  new MapStudentRepository();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        for(int i=1;i<=10;i++){
            String id= "s"+i;
            String name = "실습생"+i;
            int age = rand.nextInt(10)+20;
            int genderDeafult = rand.nextInt(2);
            Gender gender = genderDeafult == 0? Gender.M: Gender.F;
            Student student = new Student(id, name, gender,age);
            studentRepository.save(student);
        }
        return studentRepository;
    }
    @Bean
    public UserRepositoryInp userRepository(){
        UserRepositoryInp userRepository = new UserRepository();
        userRepository.addUser("admin","1234");
        return userRepository;
    }
}