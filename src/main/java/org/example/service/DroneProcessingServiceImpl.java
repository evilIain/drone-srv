package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.converter.DroneMapper;
import org.example.dto.in.RegisterDroneRequest;
import org.example.dto.out.RegisterDroneResponse;
import org.example.entity.DroneEntity;
import org.example.service.db.DroneService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DroneProcessingServiceImpl implements DroneProcessingService {

    private final DroneMapper droneMapper;
    private final DroneService droneService;
    @Override
    public RegisterDroneResponse registerDrone(RegisterDroneRequest request) {
        final DroneEntity droneEntity = droneMapper.toDroneEntity(request);

        final DroneEntity dbDroneEntity = droneService.register(droneEntity);

        return droneMapper.toRegisterDroneResponse(dbDroneEntity);
    }
}
