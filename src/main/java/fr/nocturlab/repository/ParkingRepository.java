package fr.nocturlab.repository;

import fr.nocturlab.model.Parking;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ParkingRepository extends PagingAndSortingRepository<Parking, Integer> {

    List<Parking> findByPlacesDisponiblesIsGreaterThan(Integer x);

}
