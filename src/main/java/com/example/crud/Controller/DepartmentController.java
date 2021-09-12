package com.example.crud.Controller;

import com.example.crud.model.Department;
import com.example.crud.model.Employee;
import com.example.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;


    @GetMapping("")
    public List<Department> listAllDepartment() {
        return departmentService.listAll();
    }

    @PostMapping("")
    public ResponseEntity<String> CreateNewDepartment(@RequestBody Department department) {
        departmentService.saveDepartment(department);
        return new ResponseEntity<String>("Department created", HttpStatus.OK);

    }

    @PostMapping("/update/{DepartmentId}")
    public ResponseEntity<String> updateDepartment(@RequestBody Department department, @PathVariable(name = "DepartmentId") Integer departmentId) {
        departmentService.updateDepartment(department, departmentId);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }


    @PostMapping("/delete/{departmentId}")
    public ResponseEntity<String> deleteDept(@PathVariable Integer departmentId) {
        try {
            departmentService.deleteDepartment(departmentId);
            return new ResponseEntity<>("Department deleted!", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Department can't be deleted", HttpStatus.FORBIDDEN);
        }

    }


}
