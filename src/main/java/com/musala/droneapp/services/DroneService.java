package com.musala.droneapp.services;

import com.musala.droneapp.enums.DroneState;
import com.musala.droneapp.models.Drone;
import com.musala.droneapp.models.Medication;
import com.musala.droneapp.repos.DroneRepository;
import com.musala.droneapp.repos.MedicationRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DroneService {

    private final MedicationRepository medicationRepository;
    private final DroneRepository droneRepository;

    public DroneService(MedicationRepository medicationRepository, DroneRepository droneRepository) {
        this.medicationRepository = medicationRepository;
        this.droneRepository = droneRepository;
    }

    public Drone registerDrone(Drone drone){
        return droneRepository.save(drone);
    }

    public String loadDrone(Long droneId, Set<Medication> medications){
        Drone drone = droneRepository.getDroneDetails(droneId);
        if(drone.getBatteryCapacity() < 25)
            return new JSONObject().put("message","Drone cannot be loaded because battery capacity is currently below 25%.").toString();

        drone.setState(DroneState.LOADING);
        droneRepository.save(drone);

        double cargoWeight = 0;
        for(Medication medication: medications){
            cargoWeight += medication.getWeight();
        }

        if(cargoWeight > drone.getWeightLimit())
            return new JSONObject().put("message","Drone cannot be loaded because medication weight of " +cargoWeight +"g exceeds drones' "+ drone.getWeightLimit() +"g capacity.").toString();

        drone.setMedications(medications);
        drone.setState(DroneState.LOADED);
        droneRepository.save(drone);

        return new JSONObject().put("message","Drone loaded successfully.").toString();
    }

//    public List<Medication> getLoadedMedication(Long droneId){
    public Set<Medication> getLoadedMedication(Long droneId){
        Drone dr = droneRepository.getDroneDetails(droneId);
        return dr.getMedications();
    }

    public List<Drone> getAvailableDrones(){
        return droneRepository.findAvailableDrones(String.valueOf(DroneState.IDLE));
    }

    public double checkDroneBatteryLevel(Long droneId){
        Drone dr = droneRepository.getDroneDetails(droneId);
        return dr.getBatteryCapacity();
    }
}
