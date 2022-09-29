package com.musala.droneapp.controllers;

import com.musala.droneapp.models.Drone;
import com.musala.droneapp.models.Medication;
import com.musala.droneapp.services.DroneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping(value ="/drone", produces = MediaType.APPLICATION_JSON_VALUE)
public class DispatchController {

    private final DroneService droneService;

    public DispatchController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping("/register")
    public Drone registerDrone(@Valid @RequestBody Drone drone, Errors errors){
        if (errors.hasErrors()) {
            log.info("Error: "+ errors.toString());
            return new Drone();
        } else {
            return droneService.registerDrone(drone);
        }
    }

    @PostMapping("/load/{droneId}")
    public String loadDrone(@PathVariable("droneId") Long droneId, @Valid @RequestBody Set<Medication> medications, Errors errors){

        if (errors.hasErrors()) {
            return errors.toString();
        } else {
            return droneService.loadDrone(droneId, medications);
        }
    }

    @GetMapping("/{droneId}/content")
    public Set<Medication> getDroneMedication(@PathVariable("droneId") Long droneId){
        return droneService.getLoadedMedication(droneId);
    }

    @GetMapping("/available")
    public List<Drone> getAvailableDrones(){
        return droneService.getAvailableDrones();
    }

    @GetMapping("/{droneId}/battery_level")
    public double checkDroneBatteryLevel(@PathVariable("droneId") Long droneId){
        return droneService.checkDroneBatteryLevel(droneId);
    }

}
