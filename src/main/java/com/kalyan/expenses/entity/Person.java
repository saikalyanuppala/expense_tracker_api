package com.kalyan.expenses.entity;

import java.time.LocalDate;
import java.util.Set;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kalyan.expenses.converter.SetToStringConverter;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PERSON")
@Getter
@Setter
public class Person extends BaseEntity {

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Range(min = 18, max = 60, message = "age must be between 18 and 60")
	private Integer age;

	@Column(name = "birth_name")
	@Past
	private LocalDate birthDate;

	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "person_emails", joinColumns = @JoinColumn(name = "person_id"), uniqueConstraints = @UniqueConstraint(columnNames = "email"))
	@Column(name = "email", unique = true)
	private Set<String> emails;

	@Convert(converter = SetToStringConverter.class)
	@Column(name = "mobile_numbers", unique = true)
	private Set<String> mobileNumbers;

	@JsonIgnore
	@Transient
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	@PrePersist
	@PreUpdate
	@PreRemove
	public void beforeAnyUpdate() {
		if (getId() == null) {
			System.out.println("[Person AUDIT] pre saving person ");
		} else {
			System.out.println("[Person AUDIT] pre update/remove for person " + getId());
		}
	}

	@PostPersist
	@PostUpdate
	@PostRemove
	private void afterAnyUpdate() {
		System.out.println("[Person AUDIT] add/update/delete complete for Person: " + getId());
	}

	@PostLoad
	private void afterLoad() {
		System.out.println("[Person AUDIT] Person loaded from database: " + getId());
	}
}
