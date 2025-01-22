package com.kalyan.expenses.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kalyan.expenses.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

	@Query("select p from Person p join p.emails e where e = :email")
	Optional<Person> findByEmail(@Param("email") String email);

	//@Query(value = "select * from person p where p.mobile_numbers like %:number%", nativeQuery = true)
	//Optional<Person> findByMobileNumberLike(@Param("number") String number);
}
