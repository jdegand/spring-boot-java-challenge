package com.example.spring_boot_java_challenge.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDTO {

    //@Size(max = 255)
    //@TicketIdValid
    private String id;

    @Size(max = 255)
    private String origin;

    @Size(max = 255)
    private String destination;

    private Integer departureDate;

    private Integer time;

    //private Duration duration;

    @Size(max = 255)
    private String personId;

}
