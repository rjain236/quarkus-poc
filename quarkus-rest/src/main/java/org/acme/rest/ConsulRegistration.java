package org.acme.rest;

import io.quarkus.runtime.StartupEvent;
import io.vertx.ext.consul.ConsulClientOptions;
import io.vertx.ext.consul.ServiceOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.consul.ConsulClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class ConsulRegistration {

  @ConfigProperty(name = "consul.host")
  String host;

  @ConfigProperty(name = "consul.port")
  int port;

  @ConfigProperty(name = "quarkus.http.port")
  int myPort;

  @ConfigProperty(name = "quarkus-rest.host")
  String myHost;

  public void init(@Observes StartupEvent ev, Vertx vertx) {
    ConsulClient client =
        ConsulClient.create(vertx, new ConsulClientOptions().setHost(host).setPort(port));

    client.registerServiceAndAwait(
        new ServiceOptions()
            .setPort(myPort)
            .setAddress(myHost)
            .setName("my-rest-service")
            .setId("rest-service"));

    client.registerServiceAndAwait(
        new ServiceOptions()
            .setPort(9000)
            .setAddress(myHost)
            .setName("my-grpc-service")
            .setId("grpc-service"));
  }
}
