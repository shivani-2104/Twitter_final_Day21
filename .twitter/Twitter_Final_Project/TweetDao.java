package com.company.Twitter_Final_Project;

import java.util.List;

public interface TweetDao {
    List readAll();
    void create(Tweet tweet);
//    List readByEmail(String email);
//    void delete(String email,String password);

}
