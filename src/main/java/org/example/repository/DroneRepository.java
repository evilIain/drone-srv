package org.example.repository;

import org.example.entity.DroneEntity;
import org.example.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<DroneEntity, String> {

    List<DroneEntity> findAllByStateInAndBatteryCapacityGreaterThan(List<State> states, Integer batteryCapacity);
}
