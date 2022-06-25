package com.IsaMrsTim19.projekat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.model.Review;

@Repository
public interface ReviewRepository extends Neo4jRepository<Review, Long> {
	
	@Query("MATCH (r:Review) WHERE id(r) = $id RETURN r")
	Optional<Review> findById(Long id);

	@Query("MATCH (n:Client)-[:MADE_REVIEW]->(r:Review) WHERE id(n) = $id RETURN r")
	List<Review> getReviewsByClientId(Long id);

	@Query("MATCH (n:Client)-[:MADE_REVIEW]->(r:Review) WHERE n.email = $email RETURN r")
	List<Review> getReviewsByClientEmail(String email);

	@Query("MATCH (n:Client)-[:MADE_REVIEW]->(r:Review {isAccepted : false}) RETURN r")
	List<Review> getReviewsThatAreNotAccepted();

	@Query("MATCH (n:Offer)-[:HAS_REVIEW]->(r:Review {isAccepted : true}) WHERE id(n) = $id RETURN r")
	List<Review> getReviewsByOfferId(Long id);
	


}
