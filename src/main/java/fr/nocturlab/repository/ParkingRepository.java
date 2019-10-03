package fr.nocturlab.repository;

import fr.nocturlab.model.Parking;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ParkingRepository extends PagingAndSortingRepository<Parking, Integer> {

    Parking getByPlacesDisponiblesIsGreaterThan(Integer x);

}
