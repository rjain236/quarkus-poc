package org.acme.gateway;

import org.acme.rest.GreetingRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/gateway")
public class GatewayResource {

  @RestClient GreetingRestClient service;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String callRest() {
    System.out.println("making call to the service");
    return service.get();
  }
}
