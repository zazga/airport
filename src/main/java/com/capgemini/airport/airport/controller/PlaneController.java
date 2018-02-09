package com.capgemini.airport.airport.controller;

import com.capgemini.airport.airport.exception.InvalidModelException;
import com.capgemini.airport.airport.model.Plane;
import com.capgemini.airport.airport.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

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
        plane.validate();

        try {
            this.planeRepository.save(plane);
            return plane;
        } catch(DataIntegrityViolationException e){
            throw new InvalidModelException("This plane already exists!");
        }
    }

    //MAKE EDITING WORK AGAIN
//    @RequestMapping(value = "edit", method = RequestMethod.POST)
//    public Plane updatePlane(@RequestBody Plane plane) throws InvalidModelException {
//        plane.validate();
//
//        try {
//            this.planeRepository.save(plane);
//            return plane;
//        } catch(DataIntegrityViolationException e){
//            throw new InvalidModelException("This plane already exists!");
//        }
//    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deletePlane(@PathVariable long id) {
        this.planeRepository.delete(id);
    }
}
