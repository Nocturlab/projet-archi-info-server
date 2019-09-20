package fr.nocturlab.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.nocturlab.model.Group;

@RepositoryRestResource
public interface GroupRepository extends PagingAndSortingRepository<Group, Integer> {
	
}