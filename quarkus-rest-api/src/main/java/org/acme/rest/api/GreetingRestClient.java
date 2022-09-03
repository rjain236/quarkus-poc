package org.acme.rest.api;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "stork://my-rest-service")
public interface GreetingRestClient {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    String get();
}
