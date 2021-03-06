package com.IsaMrsTim19.projekat.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.model.Reservation;


@Repository
public interface ReservationRepository  extends Neo4jRepository<Reservation, Long> {

	@Query("MATCH (n:Client)-[:MADE_RESERVATION]->(r:Reservation) WHERE id(n) = $clientId RETURN r")
	List<Reservation> getReservationsByClientId(Long clientId);

	@Query("MATCH (n:Client)-[:MADE_RESERVATION]->(r:Reservation) WHERE n.email = $email RETURN r")
	List<Reservation> getReservationsFromEmail(String email);

}
