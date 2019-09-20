package fr.nocturlab.repository;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.nocturlab.model.User;

@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	User getByTokenAndPass(UUID token, byte[] encryptPassword);
	User getByEmailAndPass(String email, byte[] encryptPassword);
	User getByPseudoAndPass(String pseudo, byte[] encryptPassword);

	Stream<User> findByPassIsNull();

	int countByPassIsNull();
	
	default UUID generateToken(){
		return UUID.randomUUID();
	}

}