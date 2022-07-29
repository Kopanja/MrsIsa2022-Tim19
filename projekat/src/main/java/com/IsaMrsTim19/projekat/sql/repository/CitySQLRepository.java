package com.IsaMrsTim19.projekat.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.sql.model.City;


@Repository
public interface CitySQLRepository  extends JpaRepository<City, Long> {

}
