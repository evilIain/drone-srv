package org.example.converter;

import org.example.dto.Medication;
import org.example.dto.in.RegisterDroneRequest;
import org.example.dto.out.BaseDroneResponse;
import org.example.dto.out.LoadDroneResponse;
import org.example.dto.out.RegisterDroneResponse;
import org.example.entity.DroneEntity;
import org.example.entity.MedicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DroneMapper {

    @Mappings({
            @Mapping(source = "batteryCapacity", target = "batteryCapacity", defaultValue = "100"),
            @Mapping(source = "state", target = "state", defaultValue = "IDLE")
    })
    DroneEntity toDroneEntity(RegisterDroneRequest request);

    BaseDroneResponse toDroneBaseResponse(DroneEntity droneEntity);

    List<BaseDroneResponse> toDroneBaseResponseList(List<DroneEntity> droneEntities);

    RegisterDroneResponse toRegisterDroneResponse(DroneEntity droneEntity);

    LoadDroneResponse toLoadDroneResponse(DroneEntity droneEntity);

    List<Medication> toMedications(List<MedicationEntity> medicationEntities);

    MedicationEntity toMedicationEntity(Medication medication);

    List<MedicationEntity> toMedicationEntityList(List<Medication> medications);
}
