package by.novitsky.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
public class HelloServerController {

    @GetMapping("/hello")
    public LocalDateTime hello(){
        return LocalDateTime.now();
    }

    @GetMapping("/failing")
    public String failingRequest(@RequestParam(defaultValue = "50") Integer failurePercent){
        Integer isFailed = (int) (Math.random() * 100) + 1;
        if (failurePercent > isFailed){
            throw new RuntimeException("Failed, rnd = " + isFailed);
        }
        return "Not failed, rnd = " + isFailed;
    }

}
