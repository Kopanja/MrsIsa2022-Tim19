package com.IsaMrsTim19.projekat.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.model.FishingTour;


@Repository
public interface FishingTourRepository extends Neo4jRepository<FishingTour, Long> {
	
	@Query("MATCH(n:FishingTour)-[r:IS_IN]->(c:City)\r\n"
			+ "RETURN c,r,n ORDER BY n.rating DESC SKIP (8*$pageNum - 8) LIMIT (8)")
	public List<FishingTour> getFishingToursByPage(int pageNum);

	@Query("MATCH(n:FishingTour) RETURN COUNT(n)")
	public int getNumberOfFishingTours();

}
