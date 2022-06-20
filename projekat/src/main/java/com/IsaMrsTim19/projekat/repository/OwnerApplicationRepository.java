package com.IsaMrsTim19.projekat.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.model.OwnerApplication;


@Repository
public interface OwnerApplicationRepository extends Neo4jRepository<OwnerApplication, Long> {

	@Query("MATCH (n:OwnerApplication) WHERE id(n) = $id RETURN n")
	OwnerApplication getById(Long id);
	
}
