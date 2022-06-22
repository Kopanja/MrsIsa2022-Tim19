package com.IsaMrsTim19.projekat.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.model.Client;


@Repository
public interface ClientRepository extends Neo4jRepository<Client, Long> {

	@Query("MATCH (n:Client)-[MADE_REVIEW]->(r:Review) WHERE id(r) = $id RETURN n")
	Client findClientByReviewId(Long id);

}
