package com.IsaMrsTim19.projekat.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.model.Accommodation;
import com.IsaMrsTim19.projekat.model.Offer;

@Repository
public interface OfferRepository extends Neo4jRepository<Offer, Long> {
	
	@Query("MATCH(n:Offer)-[r:IS_IN]->(c:City)\r\n"
			+ "RETURN c,r,n ORDER BY n.rating DESC SKIP (8*$pageNum - 8) LIMIT (8)")
	public List<Offer> getOffersByPage(Integer pageNum);

	@Query("MATCH(n:Offer) RETURN COUNT(n)")
	public int getNumberOfOffers();
	
	@Query("CALL apoc.cypher.run($s, {}) YIELD value RETURN value.n")
	public List<Offer> customQuery(String s);
}
