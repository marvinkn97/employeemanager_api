package dev.marvin.employeemanagement.dao;

import dev.marvin.employeemanagement.model.Employee;
import dev.marvin.employeemanagement.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Primary
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    private final EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);

    }
}
