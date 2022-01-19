package com.microservicios.userservice.feignclients;

import com.microservicios.userservice.model.Bike;
import com.microservicios.userservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service")
@RequestMapping("/car")
public interface CarFeignClient {

    @PostMapping
    Car saveCar(@RequestBody Car car);

    @GetMapping("/user/{id}")
    List<Car> getCarsByUser(@PathVariable Long id);
}
