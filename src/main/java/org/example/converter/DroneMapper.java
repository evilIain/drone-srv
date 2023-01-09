package org.example.converter;

import org.example.dto.in.RegisterDroneRequest;
import org.example.dto.out.RegisterDroneResponse;
import org.example.entity.DroneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DroneMapper {

    @Mappings({
            @Mapping(source = "batteryCapacity", target = "batteryCapacity", defaultValue = "100"),
            @Mapping(source = "state", target = "state", defaultValue = "IDLE")
    })
    DroneEntity toDroneEntity(RegisterDroneRequest request);

    RegisterDroneResponse toRegisterDroneResponse(DroneEntity droneEntity);
}
