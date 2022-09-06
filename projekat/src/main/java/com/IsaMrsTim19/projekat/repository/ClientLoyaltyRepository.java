package com.IsaMrsTim19.projekat.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.model.ClientLoyalty;


@Repository
public interface ClientLoyaltyRepository extends Neo4jRepository<ClientLoyalty, Long>{

	
	@Query("MATCH (n:Client)-[:HAS_LOYALTY]->(l:ClientLoyalty) WHERE id(n) = $clientId RETURN l")
	ClientLoyalty getLoyaltyBeneffit(Long clientId);
}
