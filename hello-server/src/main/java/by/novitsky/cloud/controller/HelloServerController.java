package by.novitsky.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloServerController {

    @GetMapping("/hello")
    public LocalDateTime hello(){
        return LocalDateTime.now();
    }
}
