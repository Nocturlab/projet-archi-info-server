package fr.nocturlab.repository;

import fr.nocturlab.model.Tarif;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TarifRepository extends PagingAndSortingRepository {

    List<Tarif> findAll();
}
