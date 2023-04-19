package com.nhnacademy.studentmvc.repository;


import com.nhnacademy.studentmvc.domain.LoginRequest;
import com.nhnacademy.studentmvc.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository("userRepository")
public class UserRepository implements UserRepositoryInp{
    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public boolean exists(String id) {
        return userMap.containsKey(id);
    }



    @Override
    public boolean matches(LoginRequest loginRequest){
        if(Objects.isNull(getUser(loginRequest.getUserId()))){
            return false;
        }else{
            User user= userMap.get(loginRequest.getUserId());
            if(user.getUserId().equals(loginRequest.getUserId()) && user.getUserPassword().equals(loginRequest.getUserPassword())){
                return true;
            }
            return false;
        }

    }

    @Override
    public User getUser(String id) {
        return exists(id) ? userMap.get(id) : null;
    }

    @Override
    public User addUser(String id, String password) {
        return addUser(id, "admin",password);
    }

    @Override
    public User addUser(String id, String name, String password) {
        if(exists(id)){
//            throw new UserAlreadyExistsException();
            System.out.println("Error처리");
        }
        User user = new User(id, name, password);
        userMap.put(id, user);
        return user;
    }
    @Override
    public void modify(User user) {
        User newUser = getUser(user.getUserId());
        if(Objects.isNull(newUser)){
//            throw new UserNotFoundException();
            System.out.println("Error처리");
        }
    }
}
