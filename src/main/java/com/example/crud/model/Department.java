package com.example.crud.model;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "Department_Id")
    private Integer departmentId;

    @Column(name = "name")
    private String name;

    @Column(name = "floorNo")
    private Integer floorNo;

    public Department(Integer departmentId, String name, Integer floorNo) {
        this.departmentId = departmentId;
        this.name = name;
        this.floorNo = floorNo;

    }
    public Department(){

    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }
}
