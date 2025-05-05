package com.pezhmankasraee.jakartarestbasic.service;

import com.pezhmankasraee.jakartarestbasic.models.Student;
import com.pezhmankasraee.jakartarestbasic.persistence.StudentPersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class StudentService {

    @Inject
    private StudentPersistence studentPersistence;


    public List<Student> getAllStudents() {
        return this.studentPersistence.getAll();
    }

    public Student add(Student student) {
        return studentPersistence.save(student);
    }

    public long addAll(List<Student> studentList) {

        return (this.studentPersistence.saveAll(studentList));
    }
}
