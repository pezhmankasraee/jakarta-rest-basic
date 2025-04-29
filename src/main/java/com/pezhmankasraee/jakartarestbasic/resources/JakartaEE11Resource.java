package com.pezhmankasraee.jakartarestbasic.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author
 */
@Path("jakartaee11")
public class JakartaEE11Resource {

  String version = "21";

  @GET
  public Response ping() {
      return Response
          .ok("ping Jakarta EE " + version)
          .build();
  }
}
