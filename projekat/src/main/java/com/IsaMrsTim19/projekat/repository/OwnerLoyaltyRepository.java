package com.IsaMrsTim19.projekat.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.model.OwnerLoyalty;


@Repository
public interface OwnerLoyaltyRepository extends Neo4jRepository<OwnerLoyalty, Long> {
	
	@Query("MATCH (n:Owner)-[:HAS_LOYALTY]->(l:OwnerLoyalty) WHERE id(n) = $ownerId RETURN l")
	OwnerLoyalty getLoyaltyBeneffit(Long ownerId);
}
