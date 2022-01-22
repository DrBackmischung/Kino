package de.wi2020sebgroup1.cinema.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import de.wi2020sebgroup1.cinema.entities.Snack;

public interface SnackRepository extends CrudRepository<Snack, UUID>{

}
