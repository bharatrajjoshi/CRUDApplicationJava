package com.example.crud.service;

import com.example.crud.model.Department;
import com.example.crud.model.Employee;
import com.example.crud.repository.EmployeeRepo;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService = new EmployeeService();

    @Mock
    private EmployeeRepo employeeRepo;

    Department dept = new Department(1, "HR", 9);

    @Test
    public void listAllEmployeeTest() {

        List<Employee> empList = new ArrayList<>();
        Employee empOne = new Employee(1, "Bishesh", dept);
        Employee empTwo = new Employee(2, "Bharat", dept);

        empList.add(empOne);
        empList.add(empTwo);

        when(employeeRepo.findAll()).thenReturn(empList);
        List<Employee> list = employeeService.listAll();
        assertEquals(2, list.size());
        verify(employeeRepo, times(1)).findAll();

    }



    @Test
    public void saveEmployeeTest() {
        Employee emp = new Employee(1, "Hello", dept);
        employeeService.saveEmployee(emp);
        verify(employeeRepo, times(1)).save(emp);
    }

    @Test
    public void updateEmployeeTest() {
        Employee empOne = new Employee(1, "Bishesh", dept);
        Employee empTwo = new Employee(2, "Bharat", dept);
        when(employeeRepo.findById(1)).thenReturn(Optional.of(empOne));
        employeeService.updateEmployee(empTwo, 1);

        assertEquals(empTwo.getName(), empOne.getName());
        assertEquals(empTwo.getDepartment(), empOne.getDepartment());
    }


    @Test
    public void testDeleteEmployee() {
        Employee emp = new Employee(1, "bharat", dept);
        employeeService.saveEmployee(emp);
        employeeService.deleteEmployee(1);
        List<Employee> employeeOptional = employeeService.listAll();
        assertEquals(0, employeeOptional.size());
        verify(employeeRepo, times(1)).deleteById(1);
    }
}


