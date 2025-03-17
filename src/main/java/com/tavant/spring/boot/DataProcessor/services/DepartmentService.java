package com.tavant.spring.boot.DataProcessor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tavant.spring.boot.DataProcessor.entities.Department;
import com.tavant.spring.boot.DataProcessor.exceptions.ObjectsMalformedException;
import com.tavant.spring.boot.DataProcessor.exceptions.ResourcesNotFoundException;
import com.tavant.spring.boot.DataProcessor.repositories.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department createDepartment(Department department) throws ObjectsMalformedException {
        if (department == null || department.getName() == null || department.getHod() == null) {
            throw new ObjectsMalformedException("Department object not created properly. Cannot save.");
        }
        return departmentRepository.save(department);
    }
    
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findDepartmentById(int id) throws ResourcesNotFoundException {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Department not found"));
    }
    public Department findByNameAndCollegeId(String name, Long collegeId) throws ResourcesNotFoundException, ObjectsMalformedException {
        if (name == null || name.isEmpty() || collegeId == null) {
            throw new ObjectsMalformedException("Department name or College ID is null or empty. Cannot retrieve department.");
        }
        Department department = departmentRepository.findByNameAndCollegeId(name, collegeId);
        if (department == null) {
            throw new ResourcesNotFoundException("Department not found with name: " + name + " and college ID: " + collegeId);
        }
        return department;
    }

    @Transactional
    public int updateDepartmentName(int id, String name) throws ResourcesNotFoundException, ObjectsMalformedException {
        if (name == null || name.isEmpty()) {
            throw new ObjectsMalformedException("Department name is null or empty. Cannot update.");
        }
        Optional<Department> department = departmentRepository.findById(id);
        if (!department.isPresent()) {
            throw new ResourcesNotFoundException("Department not found with ID: " + id);
        }
        return departmentRepository.updateDepartmentName(id, name);
    }

    @Transactional
    public int updateDepartmentHod(int id, String hod) throws ResourcesNotFoundException, ObjectsMalformedException {
        if (hod == null || hod.isEmpty()) {
            throw new ObjectsMalformedException("Head of Department (HOD) is null or empty. Cannot update.");
        }
        Optional<Department> department = departmentRepository.findById(id);
        if (!department.isPresent()) {
            throw new ResourcesNotFoundException("Department not found with ID: " + id);
        }
        return departmentRepository.updateDepartmentHod(id, hod);
    }

    public void deleteDepartmentById(int id) throws ResourcesNotFoundException {
        Optional<Department> department = departmentRepository.findById(id);
        if (!department.isPresent()) {
            throw new ResourcesNotFoundException("Department not found with ID: " + id);
        }
        departmentRepository.deleteById(id);
    }
}
