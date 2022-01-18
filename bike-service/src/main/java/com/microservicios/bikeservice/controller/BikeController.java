package com.microservicios.bikeservice.controller;

import com.microservicios.bikeservice.entity.Bike;
import com.microservicios.bikeservice.service.BikeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    BikeService service;

    @GetMapping
    public ResponseEntity<List<Bike>> getAllBikes() {

        if (service.getAll().isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(service.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable Long id) {
        Bike bike = service.getBikeById(id);
        if (bike == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bike);
    }


    @PostMapping
    public ResponseEntity<?> addBike(@RequestBody Bike bike) {
        service.save(bike);
        return ResponseEntity.ok(bike);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Bike>> getBikeByUserId(@PathVariable Long id) {
        if(service.findBikeByUser(id).isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(service.findBikeByUser(id));
    }
}