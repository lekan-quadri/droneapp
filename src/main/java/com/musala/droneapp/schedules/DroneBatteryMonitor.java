package com.musala.droneapp.schedules;

import com.musala.droneapp.models.Drone;
import com.musala.droneapp.repos.DroneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
public class DroneBatteryMonitor {

    private final DroneRepository droneRepository;

    public DroneBatteryMonitor(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Scheduled(fixedDelay = 1000)
    public void monitorDrones(){

        log.info("============RUNNING DRONE MONITORING SERVICE==========");
        Iterable<Drone> drones = droneRepository.findAll();

        drones.forEach((drone) -> {
            log.info("Drone ID: "+drone.getId() +" currently has a "+drone.getBatteryCapacity()+"% battery capacity.");
        });

        log.info("============END OF CYCLE==========");
    }
}
