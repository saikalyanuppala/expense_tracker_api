package com.kalyan.expenses.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalyan.expenses.entity.Expense;
import com.kalyan.expenses.service.ExpenseService;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@GetMapping
	public ResponseEntity<List<Expense>> getAllExpenses() {
		return new ResponseEntity<List<Expense>>(expenseService.getAllExpenses(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Expense> getExpenseById(@PathVariable Integer id) {
		return ResponseEntity.ok(expenseService.getExpenseById(id));
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<Expense>> getExpenseByName(@PathVariable String name) {
		return ResponseEntity.ok(expenseService.getExpenseByName(name));
	}

	@GetMapping("/all/example")
	public ResponseEntity<List<Expense>> getAllExpensesByExample(@RequestBody Expense expense) {
		return new ResponseEntity<List<Expense>>(expenseService.findExpensesByExample(expense), HttpStatus.OK);
	}
	
	@GetMapping("/all/criteria")
	public ResponseEntity<List<Expense>> getAllExpensesByCriteria(@RequestBody Expense expense) {
		return new ResponseEntity<List<Expense>>(expenseService.findExpenseByCriteria(expense), HttpStatus.OK);
	}
	
	@GetMapping("/all/page")
	public ResponseEntity<List<Expense>> getAllExpensesByPagination(Pageable pageable) {
		return new ResponseEntity<List<Expense>>(expenseService.findExpensesByPagination(pageable).toList(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Expense> saveExpense(@RequestBody Expense expense) {
		return new ResponseEntity<Expense>(expenseService.saveExpense(expense), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteExpense(@PathVariable Integer id) {
		expenseService.deleteExpenseById(id);
		return ResponseEntity.ok("Deleted with id " + id);
	}
}
