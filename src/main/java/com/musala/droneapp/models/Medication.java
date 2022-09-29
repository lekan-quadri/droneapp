package com.musala.droneapp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="medication")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
//@NoArgsConstructor
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Pattern(regexp = "^[a-zA-Z\\d-_]$",
//            message = "allowed only letters, numbers, '-', '_'")
    private String name;
    private double weight;
//    @Pattern(regexp = "^[A-Z\\d_]$",
//            message = "allowed only upper case letters, underscore and numbers")
    private String code;
    private byte[] image;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "medications")
    @JsonIgnore
    @ToString.Exclude
    Set<Drone> drone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Medication that = (Medication) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
