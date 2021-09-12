package com.example.crud.service;

import com.example.crud.model.Department;
import com.example.crud.model.Employee;
import com.example.crud.repository.DepartmentRepo;
import com.example.crud.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;


    public List<Department> listAll() {
        return departmentRepo.findAll();
    }

    public void saveDepartment(Department department) {
        departmentRepo.save(department);
    }

    public void updateDepartment(Department department, Integer id) {
        Optional<Department> departmentOptional = departmentRepo.findById(id);
        if(department.getName()!=null) {
            departmentOptional.get().setName(department.getName());
        }
        if(department.getFloorNo()!=null) {
            departmentOptional.get().setFloorNo(department.getFloorNo());
        }
        saveDepartment(departmentOptional.get());

    }

    public void deleteDepartment(Integer id) {
        departmentRepo.deleteById(id);
    }

}
