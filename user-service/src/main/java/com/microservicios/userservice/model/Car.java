package com.microservicios.userservice.model;

import lombok.Data;

@Data
public class Car {
    private String brand;
    private String model;
    private Long userId;
}
