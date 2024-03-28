package com.example.spring_boot_java_challenge.repos;

import com.example.spring_boot_java_challenge.domain.Person;
import com.example.spring_boot_java_challenge.domain.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {

    Ticket findFirstByPersonId(Person person);

    boolean existsByIdIgnoreCase(String id);

}
