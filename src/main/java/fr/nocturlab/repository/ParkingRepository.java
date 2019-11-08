package fr.nocturlab.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;



import fr.nocturlab.model.Parking;

@RepositoryRestResource
public interface ParkingRepository extends JpaRepository<Parking, Integer> {

    Parking findByPlacesDisponiblesIsGreaterThan(Integer x);
    
}
