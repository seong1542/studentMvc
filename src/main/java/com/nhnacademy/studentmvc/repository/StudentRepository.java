package com.nhnacademy.studentmvc.repository;


import com.nhnacademy.studentmvc.domain.Student;

import java.util.List;

public interface StudentRepository {
    //학생 등록
    void save(Student student);
    //학생 수정
    void update(Student student);

    //학생 삭제
    void delete(String id);

    //학생 조회
    Student getStudentById(String id);

    //학생 리스트
    List<Student> getStudentList();

    //학생 아이디 존재여부
    boolean isExistById(String id);
}