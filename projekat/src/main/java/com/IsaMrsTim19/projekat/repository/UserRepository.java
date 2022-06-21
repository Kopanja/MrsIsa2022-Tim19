package com.IsaMrsTim19.projekat.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.model.User;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

	@Query("MATCH(u:User)-[r:HAS_ROLE]->(ro:Role) WHERE u.email = $email RETURN u,r,ro")
	Optional<User> findByEmail(String email);
	
}
