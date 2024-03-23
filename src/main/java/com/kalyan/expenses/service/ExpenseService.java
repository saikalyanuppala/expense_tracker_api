package com.kalyan.expenses.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kalyan.expenses.entity.Expense;
import com.kalyan.expenses.exception.ResourceNotFoundException;
import com.kalyan.expenses.repository.ExpenseRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private EntityManager em;

	public List<Expense> getAllExpenses() {
		return expenseRepository.findAll();
	}

	public Expense getExpenseById(Integer id) {
		return expenseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Expense not found with id " + id));
	}

	public List<Expense> getExpenseByName(String name) {
		return expenseRepository.findByName(name);
	}

	@Transactional
	public Expense saveExpense(Expense expense) {
		return expenseRepository.save(expense);
	}

	@Transactional
	public void deleteExpenseById(Integer id) {
		expenseRepository.deleteById(id);
	}

	public List<Expense> findExpensesByExample(Expense expense) {
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues();
		Example<Expense> example = Example.of(expense, matcher);
		return expenseRepository.findAll(example);
	}

	public List<Expense> findExpenseByCriteria(Expense expense) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Expense> cq = cb.createQuery(Expense.class);
		Root<Expense> root = cq.from(Expense.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (expense.getId() != null) {
			predicates.add(cb.equal(root.get("id"), expense.getId()));
		}
		if (expense.getName() != null) {
			predicates.add(cb.equal(root.get("name"), expense.getName()));
		}
		if (expense.getAmount() != null) {
			predicates.add(cb.equal(root.get("amount"), expense.getAmount()));
		}
		if (expense.getCategory() != null) {
			predicates.add(cb.equal(root.get("category"), expense.getCategory()));
		}
		if (expense.getDate() != null) {
			predicates.add(cb.equal(root.get("date"), expense.getDate()));
		}
		cq.where(predicates.toArray(new Predicate[0]));
		return em.createQuery(cq).getResultList();
	}

	public Page<Expense> findExpensesByPagination(Pageable pageable) {
		return expenseRepository.findAll(pageable);
	}
}
