package com.epf.cloud.ehealth_cloud.client.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.epf.cloud.ehealth_cloud.client.data.AnalyseRepository;
import com.epf.cloud.ehealth_cloud.client.model.Analyse;
import com.epf.cloud.ehealth_cloud.common.WelcomeMessage;

@Path("/analyse")
@Stateless
public class AnalyseService {

    @Inject
    private Logger log;
    
    @Inject
    private AnalyseRepository repository;

    /**
     * Creates a new member from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitRequest(Analyse analyse) {

        Response.ResponseBuilder builder = null;

        try {
        	log.info("analyse for patient request of " + analyse.getUsrAge() + " years old" );
            // Validates member using bean validation            
        	WelcomeMessage message = repository.saveRequest(analyse);      
        	
            // Create an "ok" response
            builder = Response.ok().entity(message);
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }
    
    @GET
    @Path("/checkConnect")
    @Produces(MediaType.APPLICATION_JSON)
    public WelcomeMessage checkingConnect() {
        return repository.checkingConnect();
    	
    }
    @GET
    @Path("/checkSQL")
    @Produces(MediaType.APPLICATION_JSON)
    public WelcomeMessage checkingSqlConnect() {
        return repository.checkingSqlConnect();
    }    
}
