package com.example.spring_boot_java_challenge.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {

    //@Size(max = 255)
    //@PersonIdValid
    //private String id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    @Email
    private String email;

}
