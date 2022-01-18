package com.microservicios.userservice.controller;

import com.microservicios.userservice.entity.User;
import com.microservicios.userservice.model.Bike;
import com.microservicios.userservice.model.Car;
import com.microservicios.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {

        if (service.getAll().isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(service.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = service.getUserById(id);
        if (user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }


    @PostMapping
    public ResponseEntity<?> getUserById(@RequestBody User user) {
        service.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/bikes/{id}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable Long id){
        User user = service.getUserById(id);
        if (user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(service.getBikes(id));
    }


    @GetMapping("/cars/{id}")
    public ResponseEntity<List<Car>> getCar(@PathVariable Long id){
        User user = service.getUserById(id);
        if (user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(service.getCars(id));
    }
}
