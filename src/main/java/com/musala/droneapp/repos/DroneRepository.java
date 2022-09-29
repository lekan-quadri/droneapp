package com.musala.droneapp.repos;

import com.musala.droneapp.models.Drone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends CrudRepository<Drone,Long> {

    @Query(value ="SELECT * FROM drone where id = :id",
            nativeQuery = true)
    Drone getDroneDetails(Long id);

    @Query(value ="SELECT * FROM drone where state = :state",
            nativeQuery = true)
    List<Drone> findAvailableDrones(String state);
}

