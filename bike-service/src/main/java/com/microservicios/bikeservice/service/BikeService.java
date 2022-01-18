package com.microservicios.bikeservice.service;


import com.microservicios.bikeservice.entity.Bike;
import com.microservicios.bikeservice.repository.BikeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BikeService {

    @Autowired
    BikeRepository repository;

    public List<Bike> getAll (){
        return repository.findAll();
    }

    public Bike getBikeById (Long id){
        return repository.findById(id).orElse(null);
    }

    public Bike save (Bike bike){
        return repository.save(bike);
    }

    public List<Bike> findBikeByUser(Long id){
        return repository.findByUserId(id);
    }
}
