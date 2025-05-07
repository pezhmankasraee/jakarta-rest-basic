package com.pezhmankasraee.jakartarestbasic.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@Builder (setterPrefix = "with")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String fullname;

    @NotNull
    @Min(1900)
    @Max(2100)
    private long yearOfBirth;

    @Email
    private String email;

    @NotEmpty
    private String nationality;

    @NotEmpty
    private String fieldOfStudy;
}
