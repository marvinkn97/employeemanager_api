package dev.marvin.employeemanagement.service;

import dev.marvin.employeemanagement.dao.EmployeeDAO;
import dev.marvin.employeemanagement.dto.EmployeeDTO;
import dev.marvin.employeemanagement.exception.EmployeeNotFoundException;
import dev.marvin.employeemanagement.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeDAO employeeDAO;

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeDAO.getEmployeeById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id [%s] not found".formatted(employeeId)));
    }

    public Employee createEmployee(EmployeeDTO employeeRegistrationRequest) {
        Employee newEmployee = Employee.builder()
                .name(employeeRegistrationRequest.name())
                .email(employeeRegistrationRequest.email())
                .phoneNumber(employeeRegistrationRequest.phoneNumber())
                .jobTitle(employeeRegistrationRequest.jobTitle())
                .imageUrl(employeeRegistrationRequest.imageUrl())
                .employeeCode(UUID.randomUUID().toString().substring(0, 6)).build();

        return employeeDAO.save(newEmployee);
    }

    public Employee updateEmployee(Long employeeId, EmployeeDTO employeeUpdateRequest) {
        Employee employee = employeeDAO.getEmployeeById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id [%s] not found".formatted(employeeId)));

        employee.setName(employeeUpdateRequest.name());
        employee.setEmail(employeeUpdateRequest.email());
        employee.setPhoneNumber(employeeUpdateRequest.phoneNumber());
        employee.setJobTitle(employeeUpdateRequest.jobTitle());
        employee.setImageUrl(employeeUpdateRequest.imageUrl());

        return employeeDAO.save(employee);
    }

    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeDAO.getEmployeeById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee with id [%s] not found".formatted(employeeId)));

        if (employee != null) {
            employeeDAO.deleteEmployee(employee);
        }
    }

}
