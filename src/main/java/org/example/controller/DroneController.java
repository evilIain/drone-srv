package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.in.LoadDroneRequest;
import org.example.dto.in.RegisterDroneRequest;
import org.example.dto.out.LoadDroneResponse;
import org.example.dto.out.RegisterDroneResponse;
import org.example.service.DroneProcessingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1.0/drone")
@RequiredArgsConstructor
public class DroneController {

    private final DroneProcessingService droneProcessingService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public RegisterDroneResponse registerDrone(@Valid @RequestBody RegisterDroneRequest request) {
        return droneProcessingService.registerDrone(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{droneId}")
    public LoadDroneResponse loadDroneWithMedications(@RequestBody LoadDroneRequest request, @PathVariable String droneId) {
        return droneProcessingService.loadDroneWithMedications(request, droneId);
    }
}
