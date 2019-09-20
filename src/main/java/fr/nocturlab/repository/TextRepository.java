package fr.nocturlab.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.nocturlab.model.Text;

@RepositoryRestResource
public interface TextRepository extends PagingAndSortingRepository<Text, Integer> {
	
}