package com.IsaMrsTim19.projekat.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.IsaMrsTim19.projekat.model.FishingEquipment;


public interface FishingEquipmentRepository extends Neo4jRepository<FishingEquipment, Long> {

}
