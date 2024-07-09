package com.EmpSpringBoot.Emp_SpringBoot_Backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmpSpringBoot.Emp_SpringBoot_Backend.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
