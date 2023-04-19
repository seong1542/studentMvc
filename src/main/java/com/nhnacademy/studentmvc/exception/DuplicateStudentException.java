package com.nhnacademy.studentmvc.exception;

public class DuplicateStudentException extends RuntimeException{
    public DuplicateStudentException(){
        super("학생아디가 중복되었습니다.");
    }
}
