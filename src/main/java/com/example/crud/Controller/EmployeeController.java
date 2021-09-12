package com.example.crud.Controller;

import com.example.crud.model.Employee;
import com.example.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public List<Employee> listAllEmployees() {
        return employeeService.listAll();
    }

    @PostMapping("")
    public void add(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
    }

    @PostMapping("/update/{employeeId}")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee, @PathVariable Integer employeeId) {
        employeeService.updateEmployee(employee, employeeId);
        return new ResponseEntity<>("update done!", HttpStatus.OK);
    }

    @PostMapping("/delete/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @PostMapping("/assign/{employeeId}/to/{departmentId}")
    public ResponseEntity<String>assignDepartment(@PathVariable(name = "employeeId") Integer employeeId, @PathVariable(name = "departmentId") Integer departmentId) {
        employeeService.assignDepartment(departmentId, employeeId);
        return new ResponseEntity<>("assigned!!", HttpStatus.OK);
    }
}

