package com.example.demo;

import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface PizzaRepo extends MongoRepository<Pizza, String> {

     String findByType(String a);
    List<String> findBySize(String b);
    List<String> findByToppings(String c);

}
