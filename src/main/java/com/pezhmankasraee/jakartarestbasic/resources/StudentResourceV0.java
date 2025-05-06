package com.pezhmankasraee.jakartarestbasic.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pezhmankasraee.jakartarestbasic.dto.BulkStudentRequest;
import com.pezhmankasraee.jakartarestbasic.models.Student;
import com.pezhmankasraee.jakartarestbasic.service.StudentService;
import jakarta.inject.Inject;
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
    public Response addStudent(String studentJsonString) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Student student = objectMapper.readValue(studentJsonString, Student.class);
            Student studentResponse = studentService.add(student);
            return Response.ok(studentResponse).status(Response.Status.ACCEPTED).build();
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

            Long numberOfRecordsStored = studentService.addAll(studentList);
            String responseJson = createResponseJson(numberOfRecordsStored, objectMapper);

            return Response.ok(responseJson).status(Response.Status.ACCEPTED).build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
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

    private String createResponseJson(Long numberOfRecordsStored, ObjectMapper objectMapper) throws JsonProcessingException {
        Map<String, Long> responseMap = new HashMap<>();
        responseMap.put("numberOfStoredStudents", numberOfRecordsStored);

        return objectMapper.writeValueAsString(responseMap);
    }

}
