package com.pezhmankasraee.jakartarestbasic.service;

import com.pezhmankasraee.jakartarestbasic.models.Student;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class StudentService {

    private final List<Student> studentList = new ArrayList<>();


    public List<Student> getAllStudents() {
        return this.studentList;
    }

    public Student add(Student student) {
        student.setId(UUID.randomUUID());
        this.studentList.add(student);
        return student;
    }

    public List<Student> addAll(List<Student> studentList) {

        studentList.forEach(student -> student.setId(UUID.randomUUID()));
        this.studentList.addAll(studentList);
        return (studentList);
    }
}
