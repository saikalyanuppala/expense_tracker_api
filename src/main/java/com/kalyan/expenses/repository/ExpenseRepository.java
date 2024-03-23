package com.kalyan.expenses.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kalyan.expenses.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
 
	List<Expense> findByName(String name);
}
