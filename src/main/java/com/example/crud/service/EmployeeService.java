package com.example.crud.service;

import com.example.crud.model.Department;
import com.example.crud.model.Employee;
import com.example.crud.repository.DepartmentRepo;
import com.example.crud.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> listAll() {
        return employeeRepo.findAll();
    }

    public void saveEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    public void updateEmployee(Employee employee, Integer id) {
        Optional<Employee> EmployeeOptional = employeeRepo.findById(id);
        if (employee.getName() != null) {
            EmployeeOptional.get().setName(employee.getName());
        }
        if (employee.getDepartment() != null)
            EmployeeOptional.get().setDepartment(employee.getDepartment());
        saveEmployee(EmployeeOptional.get());
    }

    public void deleteEmployee(Integer id) {
        employeeRepo.deleteById(id);
    }

    public void assignDepartment(Integer departmentId, Integer employeeid) {
        Optional<Department> departmentOptional = departmentRepo.findById(departmentId);
        Optional<Employee> employeeOptional = employeeRepo.findById(employeeid);
        employeeOptional.get().setDepartment(departmentOptional.get());
        saveEmployee(employeeOptional.get());

    }

}
