package fr.nocturlab.repository;

import fr.nocturlab.model.Parking;
import org.springframework.data.repository.PagingAndSortingRepository;

@RepositoryRestResource
public interface ParkingRepository extends PagingAndSortingRepository<Parking, Integer> {

    Parking findByPlacesDisponiblesIsGreaterThan(Integer x);

}
