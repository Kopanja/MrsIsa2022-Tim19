package com.IsaMrsTim19.projekat.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.model.Owner;

@Repository
public interface OwnerRepository  extends Neo4jRepository<Owner, Long> {

	
	@Query("MATCH (oa:OwnerApplication)-[:FOR_OWNER]->(n:Owner)-[r:HAS_ROLE]->(ro:Role) WHERE id(oa) = $applicationId RETURN n,r,ro")
	Owner findOwnerByApplicationID(Long applicationId);
}
