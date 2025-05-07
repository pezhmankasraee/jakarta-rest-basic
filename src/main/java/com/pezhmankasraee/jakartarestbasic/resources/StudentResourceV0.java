package com.pezhmankasraee.jakartarestbasic.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pezhmankasraee.jakartarestbasic.dto.BulkStudentRequest;
import com.pezhmankasraee.jakartarestbasic.models.Student;
import com.pezhmankasraee.jakartarestbasic.service.StudentService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("v0/students")
public class StudentResourceV0 {

    private final static Logger logger = LogManager.getLogger(StudentResourceV0.class);

    @Inject
    private StudentService studentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudents() {

        return this.studentService.getAllStudents();
    }

    @POST()
    @Path("/single")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent(@Valid Student student) {

        if (student == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Student studentResponse = studentService.add(student);
        return Response.ok(studentResponse).status(Response.Status.ACCEPTED).build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addList(@Valid List<Student> studentList) {

        if(studentList == null || studentList.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Long numberOfRecordsStored = studentService.addAll(studentList);
        String responseJson = createResponseJson(numberOfRecordsStored);

        return Response.ok(responseJson).status(Response.Status.ACCEPTED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {

        this.studentService.delete(id);
        return Response.accepted().build();
    }

    @POST
    @Path("/bulk-delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAll(BulkStudentRequest bulkStudentRequest) {

        if (bulkStudentRequest == null || bulkStudentRequest.getIds() == null || bulkStudentRequest.getIds().isEmpty()) {

            logger.error("Request is invalid");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        this.studentService.deleteAll(bulkStudentRequest.getIds());
        logger.info(bulkStudentRequest.getIds());

        return Response.accepted().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid Student student) {

        if(student == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Student updatedStudent = this.studentService.update(student);

        return Response.accepted().entity(updatedStudent).build();
    }

    private String createResponseJson(Long numberOfRecordsStored) {

        return "{ \"numberOfStoredStudents\" : " + numberOfRecordsStored + " }";
    }

}
