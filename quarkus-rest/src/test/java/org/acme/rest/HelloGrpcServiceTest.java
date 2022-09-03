package org.acme.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import io.quarkus.grpc.GrpcClient;
import io.quarkus.test.junit.QuarkusTest;

import org.acme.rest.api.HelloGrpc;
import org.acme.rest.api.HelloReply;
import org.acme.rest.api.HelloRequest;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class HelloGrpcServiceTest {

//    @GrpcClient
//    HelloGrpc helloGrpc;
//
//    @Test
//    public void testHello() {
//        HelloReply reply = helloGrpc
//                .sayHello(HelloRequest.newBuilder().setName("Neo").build()).await().atMost(Duration.ofSeconds(5));
//        assertEquals("HelloNeo!", reply.getMessage());
//    }

}
