package org.example.repository;

import org.example.entity.DroneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DroneRepository extends JpaRepository<DroneEntity, UUID> {
}
