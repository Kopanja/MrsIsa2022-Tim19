package com.IsaMrsTim19.projekat.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.IsaMrsTim19.projekat.model.NavigationEquipment;


public interface NavigationEquipmentRepository  extends Neo4jRepository<NavigationEquipment, Long> {

}
