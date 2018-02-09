package com.capgemini.airport.airport.model;

import org.hibernate.validator.constraints.Range;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Plane extends Model{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Range(min=0,max = 999)
    private int fuel;

    @Pattern(regexp="[a-zA-Z ]*",message="An airport name can consist of letters found in the latin alphabet")
    private String currentAirport;

    @ManyToOne()
    @JoinColumn(name="type_id", nullable = true)
    private Type type;

    public Plane() {
    }

    public Plane(int fuel, String currentAirport, Type type) {
        this.fuel = fuel;
        this.currentAirport = currentAirport;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCurrentAirport() {
        return currentAirport;
    }

    public void setCurrentAirport(String currentAirport) {
        this.currentAirport = currentAirport;
    }
}
