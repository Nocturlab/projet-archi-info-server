package fr.nocturlab.repository;

import fr.nocturlab.model.Tarif;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TarifRepository extends PagingAndSortingRepository<Tarif, Integer> {

    List<Tarif> findAll();
}
