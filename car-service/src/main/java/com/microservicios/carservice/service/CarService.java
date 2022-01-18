package com.microservicios.carservice.service;


import com.microservicios.carservice.entity.Car;
import com.microservicios.carservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CarService {

    @Autowired
    CarRepository repository;

    public List<Car> getAll (){
        return repository.findAll();
    }

    public Car getCarById (Long id){
        return repository.findById(id).orElse(null);
    }

    public Car save (Car car){
        return repository.save(car);
    }

    public List<Car> findCardsByUser(Long id){
        return repository.findByUserId(id);
    }
}
