package com.capgemini.airport.airport.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
public class Airport extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Pattern(regexp="[a-zA-Z ]*",message="An airport name must consist of letters found in the latin alphabet")
    private String name;

    @Range(min = -9223372036854775808l,message="fill in a longitude, as a double")
    private double longitude;

    @Range(min = -9223372036854775808l,message="fill in a latitude, as a double")
    private double latitude;

    @Range(min = 0, max = 5 ,message="amount of runways, as an int")
    private int runways;

    public Airport() {
    }

    public Airport(String name, double longitude, double latitude,int runways) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.runways = runways;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getRunways() {
        return runways;
    }

    public void setRunways(int runways) {
        this.runways = runways;
    }
}
