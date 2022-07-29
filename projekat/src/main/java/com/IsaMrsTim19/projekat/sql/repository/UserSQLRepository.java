package com.IsaMrsTim19.projekat.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IsaMrsTim19.projekat.sql.model.User;

@Repository
public interface UserSQLRepository extends JpaRepository<User, Long> {

	User findByEmail(String username);

}
