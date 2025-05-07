package com.pezhmankasraee.jakartarestbasic.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

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
    private String CourseName;

    @NotEmpty
    @Size(max = 256)
    private String Description;
}



