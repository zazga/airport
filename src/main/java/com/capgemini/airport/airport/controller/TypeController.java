package com.capgemini.airport.airport.controller;

import com.capgemini.airport.airport.exception.InvalidModelException;
import com.capgemini.airport.airport.model.Type;
import com.capgemini.airport.airport.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/types")
public class TypeController {
    @Autowired
    private TypeRepository typeRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Type> getTypes(){
        return this.typeRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Type getType(@PathVariable long id){
        return this.typeRepository.findOne(id);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Type createType(@RequestBody Type type) throws InvalidModelException {
        type.validate();

        try {
            this.typeRepository.save(type);
            return type;
        } catch(DataIntegrityViolationException e){
            throw new InvalidModelException("This type already exists!");
        }
    }

    //MAKE EDITING WORK AGAIN
//    @RequestMapping(value = "edit", method = RequestMethod.POST)
//    public Type updateType(@RequestBody Type type) throws InvalidModelException {
//        type.validate();
//
//        try {
//            this.typeRepository.save(type);
//            return type;
//        } catch(DataIntegrityViolationException e){
//            throw new InvalidModelException("This type already exists!");
//        }
//    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deleteType(@PathVariable long id) {
        this.typeRepository.delete(id);
    }
}
