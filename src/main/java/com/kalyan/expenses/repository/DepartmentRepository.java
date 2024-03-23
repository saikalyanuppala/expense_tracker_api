package com.kalyan.expenses.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kalyan.expenses.entity.Department;
import com.kalyan.expenses.projections.DepartmentNameLocation;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	@Query("select  d.name as name ,d.location as location from Department d")
	List<DepartmentNameLocation> getDeptsWithNameAndLocation();
}
