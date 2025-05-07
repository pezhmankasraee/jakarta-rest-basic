package com.pezhmankasraee.jakartarestbasic.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

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

    @ManyToMany
    @JoinTable(name = "STUDENT_COURSE",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courseSet;
}
