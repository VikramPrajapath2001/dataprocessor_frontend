package com.tavant.spring.boot.DataProcessor.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tavant.spring.boot.DataProcessor.entities.Department;
import com.tavant.spring.boot.DataProcessor.exceptions.ObjectsMalformedException;
import com.tavant.spring.boot.DataProcessor.exceptions.ResourcesNotFoundException;
import com.tavant.spring.boot.DataProcessor.services.DepartmentService;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin("http://localhost:4200")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable int id) {
        try {
            Department department = departmentService.findDepartmentById(id);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (ResourcesNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    /**
     * Retrieve a list of all departments.
     */
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    /**
     * Retrieve a department by its name and college ID.
     */
    @GetMapping("/{collegeId}/{name}")
    public ResponseEntity<Department> getDepartmentByNameAndCollegeId(@PathVariable Long collegeId, @PathVariable String name) {
        try {
            Department department = departmentService.findByNameAndCollegeId(name, collegeId);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (ResourcesNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (ObjectsMalformedException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update the name of a department.
     */
    @PutMapping("/name/{id}")
    public ResponseEntity<Integer> updateDepartmentName(@PathVariable int id, @RequestParam String name) {
        try {
            int updatedCount = departmentService.updateDepartmentName(id, name);
            return new ResponseEntity<>(updatedCount, HttpStatus.OK);
        } catch (ResourcesNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (ObjectsMalformedException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update the HOD of a department.
     */
    @PutMapping("/hod/{id}")
    public ResponseEntity<Integer> updateDepartmentHod(@PathVariable int id, @RequestParam String hod) {
        try {
            int updatedCount = departmentService.updateDepartmentHod(id, hod);
            return new ResponseEntity<>(updatedCount, HttpStatus.OK);
        } catch (ResourcesNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (ObjectsMalformedException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete a department by its ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable int id) {
        try {
            departmentService.deleteDepartmentById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourcesNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
