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
	
	@Query("CALL apoc.cypher.run($s, {}) YIELD value RETURN value.n, value.r, value.c")
	public List<Offer> customQuery(String s);

	@Query("MATCH (n:Offer)-[re:HAS_REVIEW]->(r:Review) WHERE id(r) = $id RETURN n,re,r")
	public Offer findOfferByReviewId(Long id);

	@Query("MATCH (n:Offer)-[re:HAS_SUBSCRIBER]->(u:User) WHERE u.email = $email RETURN n")
	public List<Offer> getSubscriptionsFromUserEmail(String email);

	@Query("MATCH (n:Offer)-[re:HAS_RESERVATION]->(r:Reservation) WHERE id(r) = $id RETURN n")
	public Offer getOfferFromReservationId(Long id);
	
	@Query("MATCH (n:Offer) WHERE id(n) = $offerId WITH n MATCH (c:Client) WHERE id(c) = $clientId RETURN EXISTS ((n)-[:HAS_SUBSCRIBER]->(c))")
	public boolean isUserSubscribed(Long offerId, Long clientId);

	
	@Query("MATCH (n:Offer)<-[re:HAS_OFFER]-(o:Owner) WHERE o.email = $email RETURN n")
	public List<Offer> getOfferByOwnerId(String email);

	@Query("MATCH (n:Offer) WHERE id(n) = $offerId WITH n MATCH (c:Client) WHERE id(c) = $id \r\n"
			+ "RETURN \r\n"
			+ "EXISTS ((n)-[:HAS_RESERVATION]->(:Reservation{isComplete : true})<-[:MADE_RESERVATION]-(c))")
	public boolean doesClientHaveCompletedReservation(Long offerId, Long id);
}
