package com.etiya.northwind.DataAccess.Abstracts;

import com.etiya.northwind.Entities.Concretes.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface EmployeeRepository extends JpaRepository <Employee,Integer>, PagingAndSortingRepository<Employee, Integer> {

    List<Employee> findEmployeeByReportsTo(int reportsTo);
}
