package com.dc;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication
public class DragonClubApplication {

    public static void main(String[] args) {
        SpringApplication.run(DragonClubApplication.class, args);
    }

    @Bean
    public JPAQueryFactory queryFactory(EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }

}

