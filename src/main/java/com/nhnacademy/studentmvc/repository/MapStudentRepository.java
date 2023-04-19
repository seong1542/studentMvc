package com.nhnacademy.studentmvc.repository;


import com.nhnacademy.studentmvc.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository("studentRepository")
public class MapStudentRepository implements StudentRepository{
    private Map<String, Student> studentMap = new ConcurrentHashMap<>(); // 읽을 때는 동시에 읽을 수 있지만 쓸 떄 특정 세그먼트나 버킷에 Lock걸림

    @Override
    public void save(Student student) {
        studentMap.put(student.getId(), student);
    }

    @Override
    public void update(Student student) { // 해당 키에 값을 바꾸면 이전의 값까지 맵에서 변경이 됩니다.(1,1이 존재했는데 1,3으로 변경한다고 하면 맵에는 1,3만 남음)
        studentMap.put(student.getId(),student);
    }

    @Override
    public void delete(String id) {
        studentMap.remove(id);
    }

    @Override
    public Student getStudentById(String id) { // id를 통해서 해당 학생의 정보를 찾을 수 있음.
        return studentMap.get(id);
    }

    @Override
    public List<Student> getStudentList() {
        return studentMap.values().stream().collect(Collectors.toList());
        //stream을 통해서 리스트로 변환하는 방법 -> 컬렉션을 사용함.
    }

    @Override
    public boolean isExistById(String id) {
        return studentMap.containsKey(id);
    }
}

