package com.IsaMrsTim19.projekat.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.model.Review;

@Repository
public interface ReviewRepository extends Neo4jRepository<Review, Long> {

	@Query("MATCH (n:Client)-[:MADE_REVIEW]->(r:Review) WHERE id(n) = $id RETURN r")
	List<Review> getReviewsByClientId(Long id);

}
