package com.kalyan.expenses.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalyan.expenses.projections.DepartmentNameLocation;
import com.kalyan.expenses.projections.EmployeeNameSalary;
import com.kalyan.expenses.projections.IdNameLocation;
import com.kalyan.expenses.projections.records.IdNameSalary;
import com.kalyan.expenses.repository.DepartmentRepository;
import com.kalyan.expenses.repository.EmployeeRepository;
import com.kalyan.expenses.repository.EmployeeRepository.EmpNameAge;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/projections")
@RequiredArgsConstructor
public class ProjectionsController {
	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;

	@GetMapping("/empNameSal")
	public List<EmployeeNameSalary> getEmpsNameSalary() {
		return employeeRepository.getEmployeesWithNameAndSalary();
	}

	@GetMapping("/empNameAge")
	public List<EmpNameAge> getEmpsNameAge() {
		return employeeRepository.getEmployeesWithNameAndAge();
	}

	@GetMapping("/empIdNameLoc")
	public List<IdNameLocation> getEmpsIdNameLoc() {
		return employeeRepository.getEmployeesWithIdNameLocation();
	}
	
	@GetMapping("/empIdNameSal")
	public List<IdNameSalary> getEmpsIdNameSalary() {
		return employeeRepository.findAllProjectedBy();
	}

	@GetMapping("/deptNameLoc")
	public List<DepartmentNameLocation> getDeptsNameLocation() {
		return departmentRepository.getDeptsWithNameAndLocation();
	}
}
