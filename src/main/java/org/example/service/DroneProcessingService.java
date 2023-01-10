package org.example.service;

import org.example.dto.in.LoadDroneRequest;
import org.example.dto.in.RegisterDroneRequest;
import org.example.dto.out.BaseDroneResponse;
import org.example.dto.out.GetLoadedMedicationsResponse;
import org.example.dto.out.LoadDroneResponse;
import org.example.dto.out.RegisterDroneResponse;

import java.util.List;

public interface DroneProcessingService {

    RegisterDroneResponse registerDrone(RegisterDroneRequest request);

    LoadDroneResponse loadDroneWithMedications(LoadDroneRequest request, String droneId);

    GetLoadedMedicationsResponse getLoadedMedications(String droneId);

    List<BaseDroneResponse> getAvailableDrones();
}
