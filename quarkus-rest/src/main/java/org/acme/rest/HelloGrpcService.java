package org.acme.rest;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import org.acme.rest.api.*;

@GrpcService
public class HelloGrpcService implements HelloGrpc {

  @Override
  public Uni<HelloReply> sayHello(HelloRequest request) {
    return Uni.createFrom()
        .item("Hello" + request.getName() + request.getAdditionalInfo() + "!")
        .map(msg -> HelloReply.newBuilder().setMessage(msg).build());
  }

  @Override
  public Uni<ByeReply> sayByeBye(ByeRequest request) {
    return Uni.createFrom()
        .item("Bye" + request.getName() + "!")
        .map(msg -> ByeReply.newBuilder().setMessage(msg).build());
  }
}
