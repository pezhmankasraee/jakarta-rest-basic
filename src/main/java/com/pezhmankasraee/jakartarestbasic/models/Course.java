package com.pezhmankasraee.jakartarestbasic.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Builder(setterPrefix = "with")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "COURSE")
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String courseName;

    @NotEmpty
    @Size(max = 256)
    private String description;

    @ManyToMany(mappedBy = "courseSet")
    private Set<Student> studentSet;
}



