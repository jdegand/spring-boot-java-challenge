package com.example.spring_boot_java_challenge.domain;

import jakarta.validation.constraints.Size;

//import java.time.Duration;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document("tickets")
@Getter
@Setter
public class Ticket {

    @Id
    private String id;

    @Size(max = 255)
    private String origin;

    @Size(max = 255)
    private String destination;

    private Integer departureDate;

    private Integer time;

    //private Duration duration;

    @DocumentReference(lazy = true)
    private Person personId;

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
