package de.wi2020sebgroup1.cinema.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.wi2020sebgroup1.cinema.entities.City;

@Repository
public interface CityRepository extends CrudRepository<City, UUID> {
	
	List<City> findByPlz(int plz);

}