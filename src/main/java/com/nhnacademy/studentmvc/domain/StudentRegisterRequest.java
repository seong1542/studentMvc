package com.nhnacademy.studentmvc.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Value
public class StudentRegisterRequest {
    @NotBlank(message = "아이디를 입력해주세요")
    @Length(min=5, max=20, message = "아이디는 최소 5이상, 최대 20이하입니다.")
    private String id;
    @NotBlank(message = "이름을 입력해주세요")
    private String name;


    private Gender gender;
    @Min(value = 20, message = "나이는 20살 이상입니다.")
    @Max(value = 30, message = "나이는 30살 미만입니다.")
    private int age;
}
