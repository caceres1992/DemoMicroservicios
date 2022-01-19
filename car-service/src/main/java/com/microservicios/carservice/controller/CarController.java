package com.microservicios.carservice.controller;

import com.microservicios.carservice.entity.Car;
import com.microservicios.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService service;

    @GetMapping
    public ResponseEntity<List<Car>> getAllUser() {

        if (service.getAll().isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(service.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Car car = service.getCarById(id);
        if (car == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(car);
    }


    @PostMapping
//    este es un post para carro
    public ResponseEntity<?> getUserById(@RequestBody Car car) {
        service.save(car);



        return ResponseEntity.ok(car);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable Long id) {
        if(service.findCardsByUser(id).isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(service.findCardsByUser(id));
    }
}