package com.pezhmankasraee.jakartarestbasic.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pezhmankasraee.jakartarestbasic.models.Student;
import com.pezhmankasraee.jakartarestbasic.service.StudentService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("v0/students")
public class StudentResourceV0 {

    @Inject
    private StudentService studentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudents() {

        return this.studentService.getAllStudents();
    }

    @Path("/single")
    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent(String studentJsonString) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Student student = objectMapper.readValue(studentJsonString, Student.class);
            studentService.add(student);
            return Response.accepted().build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addList(String studentJsonListOfString) {

        var objectMapper = new ObjectMapper();
        try {
            List<Student> studentList = objectMapper.readValue(studentJsonListOfString, new TypeReference<List<Student>>() {});
            studentService.addAll(studentList);
            return Response.accepted().build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

}


