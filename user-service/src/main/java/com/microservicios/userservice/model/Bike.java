package com.microservicios.userservice.model;

import lombok.Data;

@Data
public class Bike {
    private String brand;
    private String model;
    private Long userId;
}
