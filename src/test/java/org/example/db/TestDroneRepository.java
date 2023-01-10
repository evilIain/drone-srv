package org.example.db;

import org.example.entity.DroneEntity;
import org.example.repository.DroneRepository;
import org.springframework.data.jpa.repository.Query;

public interface TestDroneRepository extends DroneRepository {

    @Query(value = "SELECT d from DroneEntity d JOIN FETCH d.medications WHERE d.id = :id ")
    DroneEntity getByIdWithJoin(String id);
}
