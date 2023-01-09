package org.example.service;

import org.example.dto.in.RegisterDroneRequest;
import org.example.dto.out.RegisterDroneResponse;

public interface DroneProcessingService {

    RegisterDroneResponse registerDrone(RegisterDroneRequest request);
}
