package com.company.Twitter_Final_Project;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
@Component
public class UserDaoImp implements UserDao
{
    @Autowired
Session session;
    public List readAll()
    {
        return session.createQuery("from User",User.class).getResultList();
    }
    public void create(User user)
    {
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
    }
    public List readByEmail(String email)
    {
        Query q=session.createQuery(" from User where email=:e");
        q.setParameter("e",email);
        List lists=q.getResultList();
        return lists;
    }
    public boolean delete(String email,String password)
    {
        session.beginTransaction();
        Query q1=session.createQuery("delete from User where email=:e and password=:p");
        q1.setParameter("e",email);
        q1.setParameter("p",password);
        q1.executeUpdate();
        session.getTransaction().commit();
        return true;
    }
    public void follow(String email)//b
    {
        session.beginTransaction();

        Query q=session.createQuery("select userId from User where email=:e");
        q.setParameter("e",email);

        int x=(Integer)q.getSingleResult();
        System.out.println("x== "+x);

        User data=session.get(User.class,x);
        List list;

        Query q5=session.createQuery("select followers2 from User where userId=:u");
        q5.setParameter("u",x);
        list=q5.getResultList();

        if(list==null)
        {
            list=new ArrayList();
        }

        list.add(TwitterNewOne.cuurentEmail);//a

        data.setFollowers(list);

        data.setFollowers2(""+list);
        session.persist(data);
        session.getTransaction().commit();
    }
    public List readByEmail(String email,String password)
    {
        Query q=session.createQuery(" from User where email=:e and password=:p");
        q.setParameter("e",email);
        q.setParameter("p",password);
        List lists=q.getResultList();
        return lists;
    }
    public void update(MultiValueMap<String ,String> formData)
    {
        session.beginTransaction();

        String id=formData.get("userId").get(0);
        String name=formData.get("name").get(0);
        String email=formData.get("email").get(0);
        String password=formData.get("password").get(0);

        Query q=session.createQuery(" from User where userId=:u");
        q.setParameter("u",id);
        User data=session.get(User.class,id);
        data.setName(name);
        data.setEmail(email);
        data.setPassword(password);

        session.persist(data);
        session.getTransaction().commit();

    }
}
