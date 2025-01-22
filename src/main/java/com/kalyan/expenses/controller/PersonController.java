package com.kalyan.expenses.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalyan.expenses.entity.Person;
import com.kalyan.expenses.exception.ResourceNotFoundException;
import com.kalyan.expenses.repository.PersonRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/person")
public class PersonController {

	private final PersonRepository personRepository;

	public PersonController(PersonRepository personRepository) {
		super();
		this.personRepository = personRepository;
	}

	@GetMapping
	public ResponseEntity<List<Person>> findAllPersons() {
		List<Person> list = personRepository.findAll();
		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<Person> savePerson(@Valid @RequestBody Person person) {
		return new ResponseEntity<Person>(personRepository.save(person), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> findPersonById(@PathVariable Integer id) {
		Optional<Person> person = personRepository.findById(id);
		return ResponseEntity.ok(person.orElseThrow(() -> new ResourceNotFoundException("person not found " + id)));
	}

	@GetMapping("email/{email}")
	public ResponseEntity<Person> findPersonByEmail(@PathVariable String email) {
		Optional<Person> person = personRepository.findByEmail(email);
		return ResponseEntity.ok(person.orElseThrow(() -> new ResourceNotFoundException("person not found " + email)));
	}

//	@GetMapping("mobile/{mobile}")
//	public ResponseEntity<Person> findPersonByMobileNumber(@PathVariable String mobile) {
//		Optional<Person> person = personRepository.findByMobileNumberLike(mobile);
//		return ResponseEntity.ok(person.orElseThrow(() -> new ResourceNotFoundException("person not found " + mobile)));
//	}
}
