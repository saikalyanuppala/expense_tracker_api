package com.kalyan.expenses.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.config.Projection;

import com.kalyan.expenses.entity.Employee;
import com.kalyan.expenses.projections.EmployeeNameSalary;
import com.kalyan.expenses.projections.IdNameLocation;
import com.kalyan.expenses.projections.records.IdNameSalary;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("select e.name as name ,e.salary as salary from Employee e")
	List<EmployeeNameSalary> getEmployeesWithNameAndSalary();

	@Projection(name = "empNameAge", types = { Employee.class })
	public interface EmpNameAge {
		String getName();

		Integer getAge();
	}
	@Query("select e.name as name ,e.age as age from Employee e")
	List<EmpNameAge> getEmployeesWithNameAndAge();
	
	@Query("select e.id as id,e.name as name ,d.location as location from Employee e join e.department d")
	List<IdNameLocation> getEmployeesWithIdNameLocation();
	
	List<IdNameSalary> findAllProjectedBy();
}
