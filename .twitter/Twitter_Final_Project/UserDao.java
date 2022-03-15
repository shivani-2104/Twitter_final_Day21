package com.company.Twitter_Final_Project;

import org.springframework.util.MultiValueMap;

import java.util.List;

public interface UserDao {
    List<User> readAll();
    void create(User user);
    List readByEmail(String email);
    void follow(String email);
public boolean delete(String email,String password);
    List readByEmail(String email,String password);
    void update(MultiValueMap<String,String> formData);

}
