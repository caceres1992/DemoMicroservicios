package com.microservicios.userservice.service;

import com.microservicios.userservice.entity.User;
import com.microservicios.userservice.feignclients.BikeFeignClient;
import com.microservicios.userservice.feignclients.CarFeignClient;
import com.microservicios.userservice.model.Bike;
import com.microservicios.userservice.model.Car;
import com.microservicios.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository repository;
    @Autowired
    CarFeignClient carFeignClient;
    @Autowired
    BikeFeignClient bikeFeignClient;

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

    public  Car saveCar(Long id,Car car){
        car.setUserId(id);
        return carFeignClient.saveCar(car);
    }
    public  Bike savebike(Long id,Bike bike){
        bike.setUserId(id);
        return bikeFeignClient.saveBike(bike);
    }



    public Map<String,Object>getUserAndVehicules (Long userId){
        Map<String,Object> result = new HashMap<>();
        User user = repository.findById(userId).orElse(null);
        if (user == null)
        {
            result.put("Mensaje","Usuario no encontrado");
            return result;
        }

        result.put("User",user);
        List<Car> cars = carFeignClient.getCarsByUser(userId);
        List<Bike> bikes = bikeFeignClient.getBikesByUser(userId);
        if (cars.isEmpty()){
            result.put("Cars","Este usuario no tiene coches");
        }else{
            result.put("Cars",cars);
        }

        if (bikes.isEmpty()){
            result.put("Bikes","Este usuario no tiene Motocicleta");
        }else{
            result.put("Bikes",bikes);
        }
        return result;
    }
}
