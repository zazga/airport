package com.capgemini.airport.airport.repository;

import com.capgemini.airport.airport.model.Airport;
import org.springframework.data.repository.CrudRepository;

public interface AirportRepository extends CrudRepository<Airport, Long> {}
