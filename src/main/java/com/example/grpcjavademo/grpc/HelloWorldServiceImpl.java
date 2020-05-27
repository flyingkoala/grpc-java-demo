package com.example.grpcjavademo.grpc;


import com.example.grpc.helloworld.GreeterGrpc;
import com.example.grpc.helloworld.HelloReply;
import com.example.grpc.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GRpcService
public class HelloWorldServiceImpl
    extends GreeterGrpc.GreeterImplBase {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(com.example.grpcjavademo.grpc.HelloWorldServiceImpl.class);

  @Override
  public void sayHello(HelloRequest request,
                       StreamObserver<HelloReply> responseObserver) {
    LOGGER.info("server received {}", request);

    String message = "Java gRpc服务的响应为： Hello " + request.getName() + "!";
    HelloReply greeting =
            HelloReply.newBuilder().setMessage(message).build();
    LOGGER.info("server responded {}", greeting);

    responseObserver.onNext(greeting);
    responseObserver.onCompleted();
  }
}
