package fr.nocturlab.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.nocturlab.model.Language;

@RepositoryRestResource
public interface LanguageRepository extends PagingAndSortingRepository<Language, Integer> {
	
}