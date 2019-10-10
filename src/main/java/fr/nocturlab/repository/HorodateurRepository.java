package fr.nocturlab.repository;

import fr.nocturlab.model.Horodateur;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface HorodateurRepository extends PagingAndSortingRepository<Horodateur, Integer> {

    List<Horodateur> findAll();
}
