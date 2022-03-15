package com.company.Twitter_Final_Project;


import com.company.Day20.InvalidCredentialException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CredentialInterceptor implements HandlerInterceptor {
    @Value("${followers.allowed}")
    private boolean followers_allowed;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws InvalidCredentialException, IOException, DataNotFoundException {

        if(request.getRequestURI().equals("/create212"))
        {
            String userId=request.getParameter("userId");
            String name=request.getParameter("name");
            String email=request.getParameter("email");
             String password=request.getParameter("password");
            boolean b1=containsInvalidChars(name);
            boolean b2=emailValidator(email);
            boolean b3=passwordChecker(password);
            System.out.println(!b1+" "+b2+" "+b3);
            if(!b1 && b2 && b3)
                return true;
        }
        if(request.getRequestURI().equals("/newtweetuser"))
        {
            String email=request.getParameter("email");
            String password=request.getParameter("password");
            String tweet=request.getParameter("tweet");
            boolean b1=emailValidator(email);
            boolean b2=passwordChecker(password);
            boolean b3=tweetChecker(tweet);
            if(b1 && b2 && b3)
                return true;
        }

        if(request.getRequestURI().equals("/delete212"))
        {
            String email=request.getParameter("email");
            String password=request.getParameter("password");
            boolean b1=emailValidator(email);
            boolean b2=passwordChecker(password);
            if(b1 && b2)
                return true;
        }
        if(request.getRequestURI().equals("/login21"))
        {
            String email=request.getParameter("email");
            String password=request.getParameter("password");
            boolean b1=emailValidator(email);
            boolean b2=passwordChecker(password);
            if(b1 && b2)
                return true;
        }
        if(request.getRequestURI().equals("/follow"))
        {
            if(followers_allowed==false)
            {
                return false;
            }
            String email=request.getParameter("email");
            boolean b1=emailValidator(email);
            if(b1)
                return true;
        }
        if(request.getRequestURI().equals("/displayUserDetails") ||
                request.getRequestURI().equals("/fetchAllTweets212") ||
                request.getRequestURI().equals("/fetchUserTweets212")
        )return true;

         if(request.getRequestURI().equals("/newtweetuser"))
             throw new DataNotFoundException("Insufficient Data ,Please provide correct Information");
        System.out.println("Invalid Credentials");
        response.sendRedirect("http://localhost:63342/First_Project/templates/home.html");
        return false;
    }

    private boolean containsInvalidChars(String str) {
        boolean flag = true;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!(ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z')) {
                flag = false;
            }
        }
        return !flag;
    }
    boolean emailValidator(String value)
    {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return(matcher.matches());
    }
    boolean passwordChecker(String password)
    {
        if(password==null || password.equals(""))
            return false;
            return true;
    }
    boolean tweetChecker(String tweet)
    {
        if(tweet==null || tweet.equals(""))
            return false;
        return true;
    }

}

//        if (request.getMethod().equals("GET") && !request.getRequestURI().contains("Form")) {
//            return validateUser(request.getParameter("email"), request.getParameter("password"));
//        }
