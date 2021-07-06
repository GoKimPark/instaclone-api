package com.gokimpark.instaclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootApplication
public class InstacloneApplication {

    /*
    public static void main(String[] args) {
        SpringApplication.run(InstacloneApplication.class, args);

    }*/
    public static void main(String[] args) {
        SpringApplication.run(InstacloneApplication.class, args);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        em.close();

        emf.close();
    }
}
