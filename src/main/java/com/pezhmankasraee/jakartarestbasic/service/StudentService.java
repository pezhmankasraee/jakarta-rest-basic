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

    private final List<Student> studentList = new ArrayList<>();


    public List<Student> getAllStudents() {
        return this.studentList;
    }

    public Student add(Student student) {
        return studentPersistence.save(student);
    }

    public List<Student> addAll(List<Student> studentList) {

        this.studentList.addAll(studentList);
        return (studentList);
    }
}
