package com.IsaMrsTim19.projekat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.IsaMrsTim19.projekat.model.DeletionRequest;


public interface DeletionRequestRepository extends Neo4jRepository<DeletionRequest, Long> {

	@Query("MATCH (n:DeletionRequest)-[r:FROM_USER]->(u:User) WHERE id(n) = $id RETURN n,r,u")
	public Optional<DeletionRequest> findById(Long id);
	
	@Query("MATCH (n:DeletionRequest)-[r:FROM_USER]->(u:User)-[r2:HAS_ROLE]->(ro:Role) RETURN n,r,u,r2,ro")
	public List<DeletionRequest> findAll();
}
