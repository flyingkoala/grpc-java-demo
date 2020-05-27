package com.example.grpcjavademo.grpc;

import com.example.grpc.helloworld.GreeterGrpc;
import com.example.grpc.helloworld.HelloReply;
import com.example.grpc.helloworld.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HelloWorldClient {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(com.example.grpcjavademo.grpc.HelloWorldClient.class);

  private GreeterGrpc.GreeterBlockingStub helloWorldServiceBlockingStubForGo;
  private GreeterGrpc.GreeterBlockingStub helloWorldServiceBlockingStubForJava;

  @PostConstruct
  private void init() {
    //初始化一个连接Go grpc的客户端
    ManagedChannel managedChannelForGo = ManagedChannelBuilder
        .forAddress("127.0.0.1", 50051).usePlaintext().build();

    helloWorldServiceBlockingStubForGo =
            GreeterGrpc.newBlockingStub(managedChannelForGo);

    //初始化一个连接Java grpc的客户端
    ManagedChannel managedChannelForJava = ManagedChannelBuilder
            .forAddress("127.0.0.1", 6565).usePlaintext().build();

    helloWorldServiceBlockingStubForJava =
            GreeterGrpc.newBlockingStub(managedChannelForJava);
  }

  public String sayHello(String name,String server) {
    HelloRequest person = HelloRequest.newBuilder().setName(name).build();
    LOGGER.info("client sending {}", person);

    String res="";
    if (server=="go"){
      HelloReply greeting =
              helloWorldServiceBlockingStubForGo.sayHello(person);
      LOGGER.info("client received {}", greeting);
      res=greeting.getMessage();
    }

    if (server=="java"){
      HelloReply greeting =
              helloWorldServiceBlockingStubForJava.sayHello(person);
      LOGGER.info("client received {}", greeting);
      res=greeting.getMessage();
    }



    return res;
  }
}
