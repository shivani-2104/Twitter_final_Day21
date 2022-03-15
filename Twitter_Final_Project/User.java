package com.company.Twitter_Final_Project;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;
@Entity
public class User {
    @Id
    private int userId;
    private String name;
    private String email;
    private String password;
    private String followers2;

    @Transient
    private List followers;

    public String getFollowers2() {
        return followers2;
    }

    public void setFollowers2(String followers2) {
        this.followers2 = followers2;
    }

    public List getFollowers() {
        return followers;
    }

    public void setFollowers(List followers) {
        this.followers = followers;
    }

    @Transient
    private List tweet;

    public String getTweet2() {
        return tweet2;
    }

    public void setTweet2(String tweet2) {

        this.tweet2 = tweet2;
    }

    private String tweet2;

    public User() {
    }
//    create table User(userId int(20),name  varchar(50),email  varchar(50),password varchar(50));

    public List getTweet() {
        return tweet;
    }

    public void setTweet(List tweet) {
        this.tweet = tweet;
    }

    public User(int userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", tweet=" + tweet +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }







}
