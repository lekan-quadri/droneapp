package com.musala.droneapp.repos;

import com.musala.droneapp.models.Medication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends CrudRepository<Medication,Long> {
}
