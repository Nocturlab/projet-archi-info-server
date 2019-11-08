package fr.nocturlab.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;



import fr.nocturlab.model.Parking;
import java.util.List;

@RepositoryRestResource
public interface ParkingRepository extends JpaRepository<Parking, Integer> {

    List<Parking> findByPlacesDisponiblesIsGreaterThan(Integer x);

}
