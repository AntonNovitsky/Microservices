package by.novitsky.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="hello-server")
public interface FeignInternalService {
    @GetMapping("/hello")
    String getHello();
}
