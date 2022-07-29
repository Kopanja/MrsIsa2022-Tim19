package com.IsaMrsTim19.projekat.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.sql.model.Client;

@Repository
public interface Client2Repository extends JpaRepository<Client, Long> {

}
