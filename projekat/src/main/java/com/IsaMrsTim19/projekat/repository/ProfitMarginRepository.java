package com.IsaMrsTim19.projekat.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.model.ProfitMargin;


@Repository
public interface ProfitMarginRepository extends Neo4jRepository<ProfitMargin, Long> {

	@Query("MATCH (n:ProfitMargin) WHERE n.active = true RETURN n")
	ProfitMargin findActive();
	
	
	ProfitMargin findByActive(boolean active);

}
