package com.nhnacademy.studentmvc.repository;

import com.nhnacademy.studentmvc.domain.LoginRequest;
import com.nhnacademy.studentmvc.domain.User;

public interface UserRepositoryInp {
    boolean exists(String id);

    boolean matches(LoginRequest loginRequest);

    User getUser(String id);
    User addUser(String id, String password);
    User addUser(String id, String name, String password);
    void modify(User user);

}
