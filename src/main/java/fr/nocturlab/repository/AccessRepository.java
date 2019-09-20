package fr.nocturlab.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.nocturlab.model.Access;

@RepositoryRestResource
public interface AccessRepository extends PagingAndSortingRepository<Access, Integer> {
	
}