package com.jinkin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class JinkinsController {

    private static final Logger logger = Logger.getLogger(JinkinsController.class.getName());

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        logger.log(Level.INFO, "Received GET request at /api/hello");
        return ResponseEntity.ok("Hello, world!");
    }



    @GetMapping("/greet")
    public ResponseEntity<String> greet(@RequestParam(name = "name", defaultValue = "Guest") String name) {
        String message = "Hello, " + name + "!";
        logger.log(Level.INFO, "Received GET request at /api/greet with name: " + name);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/post")
    public ResponseEntity<String> postMessage(@RequestBody String message) {
        String responseMessage = "Received message: " + message;
        logger.log(Level.INFO, "Received POST request at /api/post with message: " + message);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
}
