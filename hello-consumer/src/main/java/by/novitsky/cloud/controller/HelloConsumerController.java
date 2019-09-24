package by.novitsky.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloConsumerController {

    private static final String PRODUCER_SERVICE_NAME = "HELLO-SERVER";
    private static final String PRODUCER_PATH = "/hello";

    private RestTemplate restTemplate;
    private DiscoveryClient discoveryClient;

    @Autowired
    public HelloConsumerController(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/hello")
    public String getHello(){
        ServiceInstance serviceInstance = discoveryClient.getInstances(PRODUCER_SERVICE_NAME).get(0);

        String url =serviceInstance.getUri().toString();
        url += PRODUCER_PATH;
        String requestResult = new RestTemplate().getForObject(url, String.class);
        requestResult = "Got data from producer: " + requestResult;

        return requestResult;
    }

}
