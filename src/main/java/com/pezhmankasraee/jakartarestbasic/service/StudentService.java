package com.pezhmankasraee.jakartarestbasic.service;

import com.pezhmankasraee.jakartarestbasic.models.Student;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class StudentService {

    private final List<Student> studentList = new ArrayList<>();


    public List<Student> getAllStudents() {
        return this.studentList;
    }

    public void add(Student student) {
        this.studentList.add(student);
    }

    public void addAll(List<Student> studentList) {
        this.studentList.addAll(studentList);
    }
}
