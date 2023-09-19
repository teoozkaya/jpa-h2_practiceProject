package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teo/message")
public class Controller {


  // localhost:8081/hello
  @GetMapping("/hello")
  public String hello() {
    return "Juice Wrld";
  }

  @GetMapping("/1/{m}")
  public String message(@PathVariable("m") String message) {
    return "Message is: " + message;
  }

  @GetMapping("/2")
  public String messageReqParameter(@RequestParam("m") String message) {
    return "Message is: " + message;
  }

}


