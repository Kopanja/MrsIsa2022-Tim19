package com.IsaMrsTim19.projekat.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.model.Role;

@Repository
public interface RoleRepository extends Neo4jRepository<Role, Long> {

	
	Role findByRole(String role);
}
