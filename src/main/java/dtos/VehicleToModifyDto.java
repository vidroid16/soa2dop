package com.example.vehicles.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleToModifyDto {
    String name;
    Integer wheelsNumber;
    Integer mileage;
    Integer enginePower;
    Double fuelConsumption;
    CoordinatesDto coordinates;
    String vehicleType;
    String fuelType;
}
