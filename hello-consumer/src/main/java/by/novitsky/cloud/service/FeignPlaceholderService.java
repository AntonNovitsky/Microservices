package by.novitsky.cloud.service;

import by.novitsky.cloud.entity.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value ="feignPlaceholderController", url = "https://jsonplaceholder.typicode.com")
public interface FeignPlaceholderService {

    @GetMapping("/posts")
    List<Item> getAllItems();

    @GetMapping("/posts/{id}")
    Item getItem(@PathVariable("id") Long id);

}
