package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.converter.DroneMapper;
import org.example.dto.in.LoadDroneRequest;
import org.example.dto.in.RegisterDroneRequest;
import org.example.dto.out.LoadDroneResponse;
import org.example.dto.out.RegisterDroneResponse;
import org.example.entity.DroneEntity;
import org.example.entity.MedicationEntity;
import org.example.exception.type.NotFoundException;
import org.example.service.db.DroneService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DroneProcessingServiceImpl implements DroneProcessingService {

    private final DroneMapper droneMapper;
    private final DroneService droneService;
    private final DroneServiceHelper droneServiceHelper;
    @Override
    public RegisterDroneResponse registerDrone(final RegisterDroneRequest request) {
        final DroneEntity droneEntity = droneMapper.toDroneEntity(request);

        final DroneEntity dbDroneEntity = droneService.register(droneEntity);

        return droneMapper.toRegisterDroneResponse(dbDroneEntity);
    }

    public LoadDroneResponse loadDroneWithMedications(final LoadDroneRequest request, String droneId) {

        Optional<DroneEntity> optionalDroneEntity = droneService.getById(droneId);

        if (optionalDroneEntity.isEmpty()) {
            throw new NotFoundException("Drone with id: " + droneId + " was not found");
        }

        DroneEntity dbDroneEntity = optionalDroneEntity.get();

        if (droneServiceHelper.isDroneValid(dbDroneEntity, request.medications())) {
            List<MedicationEntity> medications = droneMapper.toMedicationEntityList(request.medications());
            dbDroneEntity = droneService.loadDroneWithMedications(dbDroneEntity, medications);
        }

        return droneMapper.toLoadDroneResponse(dbDroneEntity);
    }
}
