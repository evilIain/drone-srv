package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.converter.DroneMapper;
import org.example.dto.in.LoadDroneRequest;
import org.example.dto.in.RegisterDroneRequest;
import org.example.dto.out.*;
import org.example.entity.DroneEntity;
import org.example.entity.MedicationEntity;
import org.example.service.db.DroneService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        DroneEntity dbDroneEntity = droneService.getById(droneId);

        if (droneServiceHelper.isDroneValid(dbDroneEntity, request.medications())) {
            List<MedicationEntity> medications = droneMapper.toMedicationEntityList(request.medications());
            dbDroneEntity = droneService.loadDroneWithMedications(dbDroneEntity, medications);
        }

        return droneMapper.toLoadDroneResponse(dbDroneEntity);
    }

    @Override
    public GetLoadedMedicationsResponse getLoadedMedications(String droneId) {
        DroneEntity dbDroneEntity = droneService.getById(droneId);

        List<MedicationEntity> currentMedications = dbDroneEntity.getMedications();

        return GetLoadedMedicationsResponse.builder()
                .medications(droneMapper.toMedications(currentMedications))
                .build();
    }

    @Override
    public List<BaseDroneResponse> getAvailableDrones() {
        List<DroneEntity> availableDroneEntities = droneService.getAvailableDrones();

        return droneMapper.toDroneBaseResponseList(availableDroneEntities);
    }

    @Override
    public CheckDroneBatteryResponse getDroneBatteryCapacity(String droneId) {
        DroneEntity dbDroneEntity = droneService.getById(droneId);

        return CheckDroneBatteryResponse.builder()
                .id(dbDroneEntity.getId())
                .batteryCapacity(dbDroneEntity.getBatteryCapacity())
                .build();
    }
}
