package com.ing.bms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ing.bms.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
