package com.example.crud.service;


import com.example.crud.model.Department;
import com.example.crud.repository.DepartmentRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTest {

    @Mock
    DepartmentRepo departmentRepo;

    @InjectMocks
    DepartmentService departmentService = new DepartmentService();

    @Test
    public void toCreateDepartment() {
        Department department = new Department(1, "tech", 3);
        departmentService.saveDepartment(department);

       verify(departmentRepo, times(1)).save(department);
    }

    @Test

    public void getAllDepartment(){
        List<Department> dept = new ArrayList<>();
        Department deptOne = new Department(1,"Hr", 7);
        Department deptTwo = new Department(2,"kbc", 2);
        dept.add(deptOne);
        dept.add(deptTwo);

        when(departmentRepo.findAll()).thenReturn(dept);

        List<Department> departments = departmentService.listAll();
        assertEquals(2, departments.size());
        assertEquals(dept.size(), departments.size());
    }

    @Test
     public void testUpdateDepartment(){
        Department oldDept = new Department(1,"HR",6);
        Department newDept = new Department(1, "system",7);
        when(departmentRepo.findById(1)).thenReturn(Optional.of(oldDept));
        departmentService.updateDepartment(newDept,1);
        assertEquals(newDept.getName(), oldDept.getName());
        assertEquals(newDept.getFloorNo(), oldDept.getFloorNo());

    }
        @Test
    public void deleteDepartmentTest(){
        Department deptdel = new Department(1,"HR",6);
        when(departmentRepo.findById(1)).thenReturn(Optional.of(deptdel));
        departmentService.deleteDepartment(1);
        List<Department> departments = departmentService.listAll();
        assertEquals(0,departments.size());
        verify(departmentRepo,times(1)).deleteById(1);

        }

}