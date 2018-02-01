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
    private int gasMeter;

    @Pattern(regexp="[a-zA-Z ]*",message="An airport name can consist of letters found in the latin alphabet")
    private String currentAirport;

    public Plane() {
    }

    public Plane(int gasMeter, String currentAirport) {
        this.gasMeter = gasMeter;
        this.currentAirport = currentAirport;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGasMeter() {
        return gasMeter;
    }

    public void setGasMeter(int gasMeter) {
        this.gasMeter = gasMeter;
    }

    public String getCurrentAirport() {
        return currentAirport;
    }

    public void setCurrentAirport(String currentAirport) {
        this.currentAirport = currentAirport;
    }
}
