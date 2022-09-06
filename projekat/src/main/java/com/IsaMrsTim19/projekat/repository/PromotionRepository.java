package com.IsaMrsTim19.projekat.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.model.Promotion;


@Repository
public interface PromotionRepository extends Neo4jRepository<Promotion, Long> {

	@Query("MATCH (n:Offer)-[re:HAS_PROMOTION]->(p:Promotion) WHERE id(n) = $offerId RETURN collect(re),p")
	List<Promotion> getPromotionsForOffer(Long offerId);

}
