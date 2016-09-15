package org.jboss.html5.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.html5.model.access.User;
import org.jboss.html5.service.UserService;

@Path("/users")
@RequestScoped
@Stateful
public class UserRest {

    @Inject
    private Logger log;
    @Inject
    private UserService service;	

    @GET
    @Path("/features")    
    @Produces( MediaType.APPLICATION_JSON)    
    public Response listAllWithFeatures() {
        Response.ResponseBuilder builder = null;
        List<User> users = new ArrayList<User>();
    	
        users = service.getAllWithFeatures();
        if (users.size() <= 0) {
            builder = Response.status(Response.Status.NO_CONTENT);
        } else {
        	builder = Response.status(Response.Status.OK).entity(users);
        }
        
    	return builder.build();    	
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        Response.ResponseBuilder builder = null;
        List<User> users = new ArrayList<User>();
        
        users = service.getAllOrderedByName();
        if (users.size() <= 0) {
            builder = Response.status(Response.Status.NO_CONTENT);
        } else {
        	builder = Response.status(Response.Status.OK).entity(users);
        }
        
    	return builder.build();
    }
    
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") long id) {
        Response.ResponseBuilder builder = null;
        
    	User user = service.getById(id);
    	
        if (user == null) {
            builder = Response.status(Response.Status.NO_CONTENT);
        } else {
        	builder = Response.status(Response.Status.OK).entity(user);
        }
        return builder.build();
    }  
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(User user) {
        Response.ResponseBuilder builder = null;

        try {
        	service.add(user);
            builder = Response.ok().entity(user);
        } catch (ConstraintViolationException ce) {
            // Handle bean validation issues
            builder = createViolationResponse(ce.getConstraintViolations());
        } catch (ValidationException e) {
            // Handle the unique constrain violation
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("email", "Email taken");
            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }

        return builder.build();
    }    
    
    private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
        log.fine("Validation completed. violations found: " + violations.size());

        Map<String, String> responseObj = new HashMap<String, String>();

        for (ConstraintViolation<?> violation : violations) {
            responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }
}
