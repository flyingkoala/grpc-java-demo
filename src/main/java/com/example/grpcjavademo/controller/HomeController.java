package com.example.grpcjavademo.controller;

import com.example.grpcjavademo.grpc.HelloWorldClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Resource
    private HelloWorldClient helloWorldClient;

    //连接Go的gRpc服务端
    @GetMapping("/testgo")
    public String testGo(){
        String s = helloWorldClient.sayHello("World","go");
        return s;
    }

    //连接java(当前项目)的gRpc服务端
    @GetMapping("/testjava")
    public String testJava(){
        String s = helloWorldClient.sayHello("World","java");
        return s;
    }
}
