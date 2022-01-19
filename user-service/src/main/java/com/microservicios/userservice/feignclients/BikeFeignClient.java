package com.microservicios.userservice.feignclients;

import com.microservicios.userservice.model.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "bike-service")
@RequestMapping("/bike")
public interface BikeFeignClient {

    @PostMapping
    Bike saveBike(@RequestBody Bike bike);
    @GetMapping("/user/{id}")
    List<Bike>getBikesByUser(@PathVariable Long id);
}
