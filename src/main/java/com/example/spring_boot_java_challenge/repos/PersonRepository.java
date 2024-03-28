package com.example.spring_boot_java_challenge.repos;

import com.example.spring_boot_java_challenge.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {

    boolean existsByIdIgnoreCase(String id);

}
