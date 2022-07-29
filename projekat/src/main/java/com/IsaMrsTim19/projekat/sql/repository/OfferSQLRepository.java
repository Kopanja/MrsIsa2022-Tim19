package com.IsaMrsTim19.projekat.sql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.sql.model.Offer;

@Repository
public interface OfferSQLRepository  extends JpaRepository<Offer, Long> {
	

}
