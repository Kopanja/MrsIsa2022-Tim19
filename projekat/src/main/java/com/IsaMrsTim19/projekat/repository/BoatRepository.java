package com.IsaMrsTim19.projekat.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.model.Boat;

@Repository
public interface BoatRepository  extends Neo4jRepository<Boat, Long> {

	@Query("MATCH(n:Boat)-[r:IS_IN]->(c:City)\r\n"
			+ "RETURN c,r,n ORDER BY n.rating DESC SKIP (8*$pageNum - 8) LIMIT (8)")
	public List<Boat> getBoatsByPage(int pageNum);

	@Query("MATCH(n:Boat) RETURN COUNT(n)")
	public int getNumberOfBoats();
}
