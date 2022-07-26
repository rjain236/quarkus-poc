package org.acme.rest;

import org.acme.common.GreetingUtil;
import org.acme.rest.api.GreetingRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource implements GreetingRestClient {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String get() {
    return GreetingUtil.hello();
  }
}
