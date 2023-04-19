package com.nhnacademy.studentmvc.domain;

import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Value
public class StudentUpdateRequest {
    @NotEmpty(message = "userName is empty")
    String name;
    Gender gender;

    @Min(0)
    int age;
}
