package com.api.resources;

import com.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.InputStream;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;


@Path("/")
public class JaxRSController {

    @Autowired
    private HelloService helloService;

    @Inject
    private ServletContext context;

    @GET
    @Path("hello")
    public Response hello() {
        InputStream resource = context.getResourceAsStream("home/home.html");
        return null == resource ? Response.status(NOT_FOUND).build() : Response.ok().entity(resource).build();
    }

    @GET
    @Path("helloMessage")
    public Response sayHello() {

        String output = "Hello world!";
        return Response.ok(output).status(200).build();
    }

    @GET
    @Path("welcome")
    public String welcome() {

        String message = helloService.welcome();
        return message;
    }
}
