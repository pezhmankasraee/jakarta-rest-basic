package com.pezhmankasraee.jakartarestbasic.resources;

import com.pezhmankasraee.jakartarestbasic.models.DeveloperInfo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("author/v0")
public class Author {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DeveloperInfo getAuthors() {
        return new DeveloperInfo();
    }
}
