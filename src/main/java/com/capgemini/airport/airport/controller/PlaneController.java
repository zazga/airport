package com.capgemini.airport.airport.controller;


import com.capgemini.airport.airport.exception.InvalidModelException;
import com.capgemini.airport.airport.model.Plane;
import com.capgemini.airport.airport.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/planes")
public class PlaneController {
    @Autowired
    private PlaneRepository planeRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Plane> getPlanes(){
        return this.planeRepository.findAll();
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Plane createPlane(@RequestBody Plane plane) throws InvalidModelException {
//        plane.validate();
        this.planeRepository.save(plane);
        return plane;
//        try {
//            this.planeRepository.save(plane);
//            return plane;
//        } catch(DataIntegrityViolationException e){
//            throw new InvalidModelException("This address already exists!");
//        }
    }
}
