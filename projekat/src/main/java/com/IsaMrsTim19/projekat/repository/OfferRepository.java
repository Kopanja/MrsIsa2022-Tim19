package com.IsaMrsTim19.projekat.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.model.Offer;

@Repository
public interface OfferRepository extends Neo4jRepository<Offer, Long> {
	
	@Query("MATCH(n:Offer)-[r:IS_IN]->(c:City)\r\n"
			+ "RETURN c,r,n ORDER BY n.rating DESC SKIP (10*$pageNum - 10) LIMIT (10*$pageNum)")
	public List<Offer> getOffersByPage(int pageNum);

}
