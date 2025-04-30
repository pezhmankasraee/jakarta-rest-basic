package com.pezhmankasraee.jakartarestbasic.models;

import jakarta.validation.constraints.Email;
import lombok.*;

@Builder (setterPrefix = "with")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String fullname;
    private long yearOfBirth;
    @Email
    private String email;
    private String nationality;
    private String fieldOfStudy;


}
