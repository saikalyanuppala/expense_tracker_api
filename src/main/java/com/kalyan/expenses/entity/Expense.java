package com.kalyan.expenses.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TBL_EXPENSES")
@Getter
@Setter
@ToString
public class Expense extends BaseEntity {

	@Column(name = "expense_name", nullable = false)
	private String name;

	private String description;

	@Column(name = "expense_amount")
	private Double amount;

	private String category;

	private LocalDate date;

	public Expense() {
	}

	public Expense(String name, String description, Double amount, String category, LocalDate date) {
		super();
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.category = category;
		this.date = date;
	}

	@PrePersist
	public void logNewExpenseAttempt() {
		System.out.println("Attempting to add new expense with name: " + name);
	}

	@PostPersist
	public void logNewExpenseAdded() {
		System.out.println("Added expense '" + name + "' with ID: " + getId());
	}

	@PreRemove
	public void logExpenseRemovalAttempt() {
		System.out.println("Attempting to delete expense with id : " + getId());
	}

	@PostRemove
	public void logExpenseRemoval() {
		System.out.println("Deleted expense : " + getId());
	}

	@PreUpdate
	public void logExpenseUpdateAttempt() {
		System.out.println("Attempting to update expense : " + name);
	}

	@PostUpdate
	public void logExpenseUpdate() {
		System.out.println("Updated expense : " + name);
	}

	@PostLoad
	public void logExpenseLoad() {
		System.out.println("Expense loaded from database");
	}
}
