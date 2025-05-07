package com.pezhmankasraee.jakartarestbasic.persistence;

import com.pezhmankasraee.jakartarestbasic.models.Student;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Stateless
public class StudentPersistence {

    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager entityManager;

    @Transactional
    public Student save(Student student) {
        entityManager.persist(student);
        entityManager.flush();
        return student;
    }

    public Student find(long id) {
       return entityManager.find(Student.class, id);
    }

    @Transactional
    public long saveAll(List<Student> studentList) {

        for(Student student : studentList) {
            entityManager.persist(student);
        }

        return studentList.size();
    }

    public List<Student> getAll() {

        return entityManager.createQuery("SELECT s FROM STUDENT s", Student.class).getResultList();
    }

    @Transactional
    public void delete(long studentId) {

        Student student = entityManager.find(Student.class, studentId);
        if(student != null) {
            entityManager.remove(student);
        }
    }

    @Transactional
    public Student update(Student student) {

        long studentId = student.getId();
        Student foundStudent = this.entityManager.find(Student.class, studentId);

        if (foundStudent != null) {
            foundStudent.setFullname(student.getFullname());
            foundStudent.setEmail(student.getEmail());
            foundStudent.setNationality(student.getNationality());
            foundStudent.setYearOfBirth(student.getYearOfBirth());
            foundStudent.setFieldOfStudy(student.getFieldOfStudy());
        }

        return foundStudent;
    }
}
