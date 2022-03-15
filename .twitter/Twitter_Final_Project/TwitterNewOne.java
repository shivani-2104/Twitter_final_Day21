package com.company.Twitter_Final_Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TwitterNewOne {
    static String cuurentEmail;
    static  String cuurentPassword;
    @Autowired
    UserDao userdao;
    @Autowired
    TweetDao tweetDao;
   List<User> listOfUsers=new ArrayList<>();
   @PostMapping(value="/create212" )
   @ResponseBody
   public ModelAndView createAccount111(@RequestBody MultiValueMap<String, String> formData) {
       ModelAndView modelAndView = new ModelAndView("register1");
       ResponseEntity<Object> responseEntity = null;
       String userId = formData.get("userId").get(0);
       String name = formData.get("name").get(0);
       String email = formData.get("email").get(0);
       String password = formData.get("password").get(0);
       int userId1 = Integer.parseInt(userId);
       /*
       No Need of validation
       as I used interceptor
       if(containsInvalidChars(name))
       {
           responseEntity = new ResponseEntity<>("name contains invalid characters",
                   HttpStatus.BAD_REQUEST);
       }
       else if(!emailValidator(email))
       {
           responseEntity = new ResponseEntity<>("Invalid Email",
                   HttpStatus.BAD_REQUEST);
       }
       else if(password=="")
       {
           responseEntity = new ResponseEntity<>("Please enter password",
                   HttpStatus.BAD_REQUEST);
       }*/

           User u1=new User(userId1,name,email,password);
           userdao.create(u1);
           listOfUsers.add(u1);
           responseEntity = new ResponseEntity<>("Creation successful",
                   HttpStatus.OK);
           System.out.println(listOfUsers);

       return modelAndView;

   }
    @PostMapping(value = "/newtweetuser", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    private ResponseEntity<Object> newtweetuser(@RequestBody MultiValueMap<String, String> formData) {


        String email = formData.get("email").get(0);
        String password = formData.get("password").get(0);
        String tweet = formData.get("tweet").get(0);
        ResponseEntity<Object> responseEntity = null;

        Tweet t1=new Tweet(email,password,tweet);
        tweetDao.create(t1);

        responseEntity = new ResponseEntity<>("Successfully Creation",
                HttpStatus.OK);
        if (responseEntity == null) {
            responseEntity = new ResponseEntity<>("Invalid UserName and Password",
                    HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping("/displayUserDetails")
    @ResponseBody
    public ModelAndView getUserDetails() {
        List list=userdao.readAll();
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.getModel().put("users", list);
        return modelAndView;
    }
    @GetMapping("/fetchAllTweets212")
    @ResponseBody
    public ModelAndView fetchTweets() {
        List<Tweet> tweets =tweetDao.readAll();
        ModelAndView modelAndView = new ModelAndView("Tweets");
        modelAndView.getModel().put("tweets", tweets);
        return modelAndView;
    }
        @PostMapping("/fetchUserTweets212")
    public ModelAndView fetchUserTweets(@RequestBody MultiValueMap<String,String> formdata) {
            String email=formdata.get("email").get(0);
            String password=formdata.get("password").get(0);
            List lists=userdao.readByEmail(email);
        ModelAndView modelAndView = new ModelAndView("SpecificUserTweets");
        modelAndView.getModel().put("lists", lists);
        return modelAndView;
    }
@PutMapping("/update1")
@ResponseBody
private ResponseEntity<Object> update1(@RequestBody MultiValueMap<String,String > formData)
{
    ResponseEntity responseEntity=null;

   userdao.update(formData);

    responseEntity = new ResponseEntity<>("Update successfully",
            HttpStatus.OK);

    return responseEntity;
}
@PostMapping("/delete212")
@ResponseBody
ResponseEntity remove(@RequestBody  MultiValueMap<String, String> formData)
{
    String email = formData.get("email").get(0);
    String password = formData.get("password").get(0);
    ResponseEntity responseEntity=null;

            userdao.delete(email,password);
            responseEntity = new ResponseEntity<>("Delete SuccessFully",
                    HttpStatus.OK);
    if(responseEntity==null)
    {
        responseEntity = new ResponseEntity<>("User doesn't exist",
                HttpStatus.BAD_REQUEST);
    }

    return responseEntity;
}

    @PostMapping("/follow")
    @ResponseBody
    public ResponseEntity follow(@RequestBody MultiValueMap <String,String > formData)
    {
        String email=formData.get("email").get(0);
        userdao.follow(email);
        ResponseEntity responseEntity=new ResponseEntity("Follow successfull",HttpStatus.OK);
        return responseEntity;
    }
    @Value("${followers.allowed}")
    private boolean followers_allowed;
    @PostMapping("/login21")
    public ModelAndView fetchUsers(@RequestBody MultiValueMap<String,String> formdata) {
        String email=formdata.get("email").get(0);
        String password=formdata.get("password").get(0);
        cuurentEmail=email;
        cuurentPassword=password;
        List lists=userdao.readByEmail(email,password);
        ModelAndView modelAndView;

        if(followers_allowed==true)
             modelAndView = new ModelAndView("particularUserDetails");
        else
            modelAndView = new ModelAndView("fileWithouFollowFunctionality");
        modelAndView.getModel().put("lists", lists);
        return modelAndView;
    }
    // CustomException Handlor
    @ExceptionHandler(value = TweetNotFoundException1.class)
    public ResponseEntity<Object> exceptionHandle(Exception exception) {
        return new ResponseEntity<>("Tweet not found", HttpStatus.NOT_FOUND);
    }
}
