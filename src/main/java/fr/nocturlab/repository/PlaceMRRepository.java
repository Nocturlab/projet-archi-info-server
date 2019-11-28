package fr.nocturlab.repository;

import fr.nocturlab.model.PlaceMR;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PlaceMRRepository extends PagingAndSortingRepository<PlaceMR, Integer> {

    List<PlaceMR> findAll();
}
