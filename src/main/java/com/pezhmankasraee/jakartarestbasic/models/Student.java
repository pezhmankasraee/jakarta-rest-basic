package com.pezhmankasraee.jakartarestbasic.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.*;

@Builder (setterPrefix = "with")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullname;
    private long yearOfBirth;
    @Email
    private String email;
    private String nationality;
    private String fieldOfStudy;
}
