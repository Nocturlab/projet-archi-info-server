package fr.nocturlab.controller;

import fr.nocturlab.model.Stationnement;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StationnementRepository extends PagingAndSortingRepository<Stationnement, Integer> {
}
