package org.acme.gateway;

import io.quarkus.runtime.StartupEvent;
import io.vertx.ext.consul.ConsulClientOptions;
import io.vertx.ext.consul.Service;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.consul.ConsulClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.stream.Collectors;

@ApplicationScoped
public class ConsulRegistration {

  @ConfigProperty(name = "consul.host")
  String host;

  @ConfigProperty(name = "consul.port")
  int port;

  public void init(@Observes StartupEvent ev, Vertx vertx) {
    ConsulClient client =
        ConsulClient.create(vertx, new ConsulClientOptions().setHost(host).setPort(port));
    System.out.println("ALl the services registered");
    System.out.println(
        client.catalogServicesAndAwait().getList().stream()
            .map(Service::getName)
            .collect(Collectors.joining()));
  }
}
