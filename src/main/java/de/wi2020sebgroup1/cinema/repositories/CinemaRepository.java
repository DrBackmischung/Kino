package de.wi2020sebgroup1.cinema.repositories;



import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import de.wi2020sebgroup1.cinema.entities.Cinema;


public interface CinemaRepository extends CrudRepository<Cinema, UUID>{


}
