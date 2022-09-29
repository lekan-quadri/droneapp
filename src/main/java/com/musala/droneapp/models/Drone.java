package com.musala.droneapp.models;


import com.musala.droneapp.enums.DroneModel;
import com.musala.droneapp.enums.DroneState;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="drone")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
//@NoArgsConstructor
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 100)
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    public DroneModel model;
    @Max(500)
    @Min(1)
    private double weightLimit;
    @Max(100)
    private double batteryCapacity;
    @Enumerated(EnumType.STRING)
    public DroneState state;

    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
        })
    @Fetch(value= FetchMode.SELECT)
    @JoinTable (
            name="cargo",
            joinColumns = {@JoinColumn(name="drone_id")},
            inverseJoinColumns = {@JoinColumn(name="medication_id")}
    )
    @ToString.Exclude
    private Set<Medication> medications;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Drone drone = (Drone) o;
        return id != null && Objects.equals(id, drone.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
