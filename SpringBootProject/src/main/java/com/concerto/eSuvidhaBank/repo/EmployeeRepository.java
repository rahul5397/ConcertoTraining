package com.concerto.eSuvidhaBank.repo;

import org.springframework.data.repository.CrudRepository;

import com.concerto.eSuvidhaBank.entity.Employee;



public interface EmployeeRepository extends CrudRepository<Employee, String> {

}
