package com.microservicios.userservice.service;

import com.microservicios.userservice.entity.User;
import com.microservicios.userservice.model.Bike;
import com.microservicios.userservice.model.Car;
import com.microservicios.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    RestTemplate restTemplate;

    public List<User> getAll (){
        return repository.findAll();
    }

    public User getUserById (Long id){
        return repository.findById(id).orElse(null);
    }

    public User save (User user){
        return repository.save(user);
    }

    public List<Car> getCars(Long id){
       List<Car>cars = restTemplate.getForObject("http://localhost:8082/car/user/" + id, List.class);

       return cars;
    }

    public List<Bike> getBikes(Long id){
        List<Bike>bikes = restTemplate.getForObject("http://localhost:8083/bike/user/"+id, List.class);
        return bikes;
    }
}
