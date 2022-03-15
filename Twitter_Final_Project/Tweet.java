package com.company.Twitter_Final_Project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tweet {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tweetId;
    private String email;
    private String password;
    private String tweet;

    public Tweet() {
    }
    @Override
    public String toString() {
        return "Tweet{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", tweet='" + tweet + '\'' +
                '}';
    }
    public Tweet(String email, String password, String tweet) {
        this.email = email;
        this.password = password;
        this.tweet = tweet;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
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
