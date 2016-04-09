package com.example.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/api")
public interface Api {

    @GET
    @Path("/test")
    Response test();

}