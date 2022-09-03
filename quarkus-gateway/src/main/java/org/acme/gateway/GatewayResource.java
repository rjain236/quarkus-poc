package org.acme.gateway;

import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;
import org.acme.rest.api.*;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/gateway")
public class GatewayResource {

  @RestClient GreetingRestClient service;

  @GrpcClient("hello")
  HelloGrpc grpcService;

  @GrpcClient("helloconsul")
  HelloGrpc grpcConsulService;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String callRest() {
    System.out.println("making call to the service");
    return service.get();
  }

  @GET
  @Path("/mutiny/{name}")
  public Uni<String> helloMutiny(String name) {
    return grpcService
        .sayHello(HelloRequest.newBuilder().setName(name).build())
        .onItem()
        .transform(HelloReply::getMessage);
  }

  @GET
  @Path("/consul/{name}")
  public Uni<String> helloConsul(String name) {
    return grpcConsulService
        .sayHello(HelloRequest.newBuilder().setName(name).build())
        .onItem()
        .transform(HelloReply::getMessage);
  }
}
