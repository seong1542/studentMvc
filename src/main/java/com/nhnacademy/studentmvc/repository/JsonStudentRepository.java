package com.nhnacademy.studentmvc.repository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.studentmvc.domain.Student;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonStudentRepository implements StudentRepository{
    private final ObjectMapper objectMapper;
    public static String path;
    public JsonStudentRepository(String stringPath) throws IOException {
        objectMapper = new ObjectMapper();
        //LocalDatetime json 직열화/역직렬화 가능하도록 설정
        objectMapper.registerModule(new JavaTimeModule());
        path = stringPath;
        Path spath = Path.of(stringPath);
        //JsonFile_PATH 경로에 json 파일이 존재하면 삭제합니다.
        if(Files.exists(spath)){
            Files.delete(spath);
        }
    }

    /*
    JSON -> 자바 object로 읽어오는 것 : readvalue;(jsonFile, java Class)
    자바 Object -> JSON으로 바뀌는 것 : writeValue; (jsonFile, java Class)
     */
    public synchronized List<Student> readJsonFile(){
        List<Student> students; // id, name,gender, age, cmd
        //json 파일이 존재하지 않는다면 비어있는 List<Student>를 반환

        File file = new File(path);
        if(!file.exists()){
            return new ArrayList<>();
        }
        // java object -> json : wrtievalue(new File("user.json", user); // user.json이라는 파일에 user 내용을 json으로 바꿔서 씀.
        //readvalue(new URL("file:user.json), User.class)해도 되고,
        //json -> java object : readvalue(json, 해당 클래스(Student.class));

        //json read&역직렬화 (json string -> Object) // 근데 여기서 리스트 형태로 쓰기 때문에 json string 에서 java list로 바꾸는 것
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))){
            students = objectMapper.readValue(bufferedReader, new TypeReference<List<Student>>(){});
            return students; // json 문자열을 버퍼에 읽어서 list<student>형태로 가져옴.
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private synchronized void writeJsonFile(List<Student> studentList){
        //List<Student>객체-> json파일로 저장 : 직렬화
        File file = new File(path);
        try(FileWriter fileWriter = new FileWriter(file); //해당 파일에 쓸 준비/
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);){
            objectMapper.writeValue(bufferedWriter, studentList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) {
        List<Student> students = readJsonFile(); // 저장한 json파일에서 읽어서 student의 리스트 형태로 만들고
        students.add(student); // 새로운 학생이 추가될 때 해당 리스트에 추가 시켜주고,
        writeJsonFile(students); // 이를 다시 json 파일 형태로 저장시킵니다.
    }

    @Override
    public void update(Student student) {
        List<Student> students = readJsonFile(); // json파일로 읽어와서 리스트로 바꾼 다음
        Student s = students.stream()
                .filter(human->human.getId().equals(student.getId()))
                .collect(Collectors.toList()).get(0);
        s.update(student.getId(), student.getName(), student.getGender(), student.getAge());
        writeJsonFile(students);
    }

    @Override
    public void delete(String id) {
        List<Student> students = readJsonFile();
        students.removeIf(human->human.getId().equals(id));
        writeJsonFile(students);
    }

    @Override
    public Student getStudentById(String id) {
        List<Student> students = readJsonFile();
        Student s = students.stream().filter(human->human.getId().equals(id)).collect(Collectors.toList()).get(0);
        if(s==null){
            return null;
        }
        return s;
    }

    @Override
    public List<Student> getStudentList() {
        return readJsonFile();
    }

    @Override
    public boolean isExistById(String id) {
        List<Student> students = readJsonFile();
        return students.stream().anyMatch(s->s.getId().equals(id));
    }
}
