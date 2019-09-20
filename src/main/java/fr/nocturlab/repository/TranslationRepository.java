package fr.nocturlab.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.nocturlab.model.Translation;

@RepositoryRestResource
public interface TranslationRepository extends PagingAndSortingRepository<Translation, Integer> {
	
}