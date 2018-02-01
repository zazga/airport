package com.capgemini.airport.airport.controller;

import com.capgemini.airport.airport.exception.InvalidModelException;
import com.capgemini.airport.airport.model.Airport;
import com.capgemini.airport.airport.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/airports")
public class AirportController {
    @Autowired
    private AirportRepository airportRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Airport> getAirport(){
        return this.airportRepository.findAll();
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Airport createAirport(@RequestBody Airport airport) throws InvalidModelException {
        airport.validate();

        try {
            this.airportRepository.save(airport);
            return airport;
        } catch(DataIntegrityViolationException e){
            throw new InvalidModelException("This airport already exists!");
        }
    }
}
