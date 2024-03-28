package com.example.spring_boot_java_challenge.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document("persons")
@Getter
@Setter
public class Person {

    @Id
    private String id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    @Email
    private String email;

    @DocumentReference(lazy = true, lookup = "{ 'personId' : ?#{#self._id} }")
    @ReadOnlyProperty
    private Set<Ticket> tickets;

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
