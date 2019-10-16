package com.ing.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.bms.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
