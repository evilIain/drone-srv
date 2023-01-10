package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.in.LoadDroneRequest;
import org.example.dto.in.RegisterDroneRequest;
import org.example.dto.out.*;
import org.example.service.DroneProcessingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0")
@RequiredArgsConstructor
public class DroneController {

    private final DroneProcessingService droneProcessingService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/drone")
    public RegisterDroneResponse registerDrone(@Valid @RequestBody RegisterDroneRequest request) {
        return droneProcessingService.registerDrone(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/drone/{droneId}")
    public LoadDroneResponse loadDroneWithMedications(@RequestBody LoadDroneRequest request, @PathVariable String droneId) {
        return droneProcessingService.loadDroneWithMedications(request, droneId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/drone/{droneId}")
    public GetLoadedMedicationsResponse getLoadedMedications(@PathVariable String droneId) {
        return droneProcessingService.getLoadedMedications(droneId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/drones")
    public List<BaseDroneResponse> getAvailableDrones() {
        return droneProcessingService.getAvailableDrones();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/drone/{droneId}/battery")
    public CheckDroneBatteryResponse getDroneBatteryCapacity(@PathVariable String droneId) {
        return droneProcessingService.getDroneBatteryCapacity(droneId);
    }
}
