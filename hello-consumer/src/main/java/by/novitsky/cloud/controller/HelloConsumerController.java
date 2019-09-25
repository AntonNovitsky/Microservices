package by.novitsky.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloConsumerController {

    private static final String PRODUCER_SERVICE_NAME = "hello-server";
    private static final String PRODUCER_PATH = "/hello";
    private static final String PRODUCER_UNSAFE_PATH = "/failing?failurePercent=";
    private static final String PRODUCER_LATENCY_PATH = "/latency?latency=";

    private RestTemplate restTemplate;
    private DiscoveryClient discoveryClient;

    @Autowired
    public HelloConsumerController(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/hello")
    public String getHello(){
        String url = getUrl(PRODUCER_PATH);
        String requestResult = new RestTemplate().getForObject(url, String.class);
        requestResult = "Got data from producer: " + requestResult;

        return requestResult;
    }

    @GetMapping("/failing")
    @HystrixCommand(fallbackMethod = "hystrixFallback")
    public String getUnsafe(@RequestParam(defaultValue = "50") Integer percent){
        String url = getUrl(PRODUCER_UNSAFE_PATH + percent);
        String requestResult = new RestTemplate().getForObject(url, String.class);
        requestResult = "From producer: " + requestResult;
        return requestResult;
    }

    private String hystrixFallback(Integer input){
        return "Error accessing " + PRODUCER_SERVICE_NAME + " with failing percent " + input;
    }

    private String getUrl(String path){
        ServiceInstance serviceInstance = discoveryClient.getInstances(PRODUCER_SERVICE_NAME).get(0);
        String url =serviceInstance.getUri().toString();
        url += path;
        return url;
    }

}
