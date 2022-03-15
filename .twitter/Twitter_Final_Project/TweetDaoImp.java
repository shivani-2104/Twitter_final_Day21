package com.company.Twitter_Final_Project;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class TweetDaoImp implements TweetDao{
    @Autowired
    Session session;
    public List readAll()
   {
       return session.createQuery("from Tweet").getResultList();
   }
public void create(Tweet tweet)
{
    session.beginTransaction();

    Query q=session.createQuery("select userId from User where email=:e and password=:p");
    q.setParameter("e",tweet.getEmail());
    q.setParameter("p",tweet.getPassword());
    int x=(Integer)q.getSingleResult();
    System.out.println("x== "+x);
    User data=session.get(User.class,x);

    List list;

    Query q5=session.createQuery("select tweet2 from User where userId=:u");
    q5.setParameter("u",x);
    list=q5.getResultList();

    if(list.contains(null) || list==null)
    {
        System.out.println("nul null");
        list=new ArrayList();
    }

    System.out.println(list);
//    List l=data.getTweet();
    list.add(tweet.getTweet());

    data.setTweet(list);

    data.setTweet2(""+list);
    session.persist(data);
    session.persist(tweet);
    session.getTransaction().commit();
}
}
