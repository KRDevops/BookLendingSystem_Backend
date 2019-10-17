package com.ing.bms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.bms.entity.Transaction;
import com.ing.bms.entity.User;
@Repository
public interface TransactionsRepository extends JpaRepository<Transaction,Integer>{

	List<Transaction> findByUserId(User userId);

}
