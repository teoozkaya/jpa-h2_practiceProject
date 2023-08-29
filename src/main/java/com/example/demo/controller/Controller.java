package com.example.demo.controller;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/teo")
public class Controller {

  private final String helloPath = "/hello";
  private final String messagePath = "/message";

  // localhost:8081/hello
  @GetMapping(path = helloPath)
  public String hello() {
    return "Juice Wrld";
  }

  @GetMapping(path = messagePath +"1/{m}")
  public String message(@PathVariable("m") String message) {
    return "Message is: " + message;
  }

  @GetMapping(path = messagePath + "2")
  public String messageReqParameter(@RequestParam("m") String message) {
    return "Message is: " + message;
  }
}


