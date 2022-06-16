package com.IsaMrsTim19.projekat.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.model.User;
import com.IsaMrsTim19.projekat.model.VerificationToken;

@Repository
public interface VerificationTokenRepository  extends Neo4jRepository<VerificationToken, Long> {
	
	VerificationToken findByToken(String token);
	
	@Query("MATCH(n:VerificationToken)-[:FOR_USER]->(:User{email : $email}) RETURN n")
	VerificationToken findByUserEmail(String email);

}
