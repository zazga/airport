package com.capgemini.airport.airport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Range;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;


@Entity
public class Type extends Model {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Range(min=0,max = 999)
    private int mileage;

    @Range(min=0,max = 999)
    private int passengers;

    @Range(min=0,max = 999)
    private int maxFuel;

    @JsonIgnore
    @OneToMany(mappedBy="type")
//    @OneToMany(mappedBy="type")
    private Set<Plane> planes;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(int maxFuel) {
        this.maxFuel = maxFuel;
    }

    public Type() {
    }

    public Type(int mileage, int passengers, int maxFuel, String name) {
        this.mileage = mileage;
        this.passengers = passengers;
        this.maxFuel = maxFuel;
        this.name = name;
    }

    //    public Type(int mileage, int passengers, String name) {
//        this.mileage = mileage;
//        this.passengers = passengers;
//        this.name = name;
//    }


}
