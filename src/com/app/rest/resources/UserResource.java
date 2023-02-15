package com.app.rest.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.app.entities.User;
import com.app.rest.model.UserModel;
import com.app.rest.service.UserService;

@Path("/users")
public class UserResource {
    private UserService userService = new UserService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> fetchAll() {
        return userService.fetchAll();
    }

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
    	
    	UserModel user = (UserModel) userService.fetchBy(id);
        return Response.ok().entity(user).build();
    }
 
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(UserModel user) {
        // create notification
        boolean resp = userService.create(user);
        String message = resp?"Sucessful!!!":"Failed!";
        return Response.status(Status.CREATED).entity(message).build();
    }
 
    @PUT
    @Path("/user/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, UserModel user) {
        userService.update(id, user);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        boolean f = userService.delete(id);
        String message = f?"User deleted successfully!!":"Can't delete users";
        
        return Response.status(202).entity(message).build();
    }
}
