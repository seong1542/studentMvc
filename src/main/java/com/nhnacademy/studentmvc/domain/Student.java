package com.nhnacademy.studentmvc.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Setter;

import java.time.LocalDateTime;

public class Student {

    private String id;
    @Setter
    private String name;
    @Setter
    private Gender gender;
    @Setter
    private int age;
    private LocalDateTime createdAt;

    public Student(){};
    @Builder
    @JsonCreator
    public Student(@JsonProperty("id") String id,
                   @JsonProperty("name") String name,
                   @JsonProperty("gender") Gender gender,
                   @JsonProperty("age") int age){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public void update(@JsonProperty("id") String id,
                       @JsonProperty("name") String name,
                       @JsonProperty("gender") Gender gender,
                       @JsonProperty("age") int age){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString(){
        return "Student{"+"id="+id+", name="+name+", gender="+gender+", age="+age+", createdAt="+createdAt+"}";
    }
}
