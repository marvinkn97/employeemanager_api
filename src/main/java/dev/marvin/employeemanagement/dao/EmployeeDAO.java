package dev.marvin.employeemanagement.dao;

import dev.marvin.employeemanagement.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {
    List<Employee> getAllEmployees();
    Employee save(Employee employee);
    Optional<Employee> getEmployeeById(Long employeeId);
    void deleteEmployee(Employee employee);

}
