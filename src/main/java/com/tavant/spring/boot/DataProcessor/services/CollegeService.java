package com.tavant.spring.boot.DataProcessor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tavant.spring.boot.DataProcessor.entities.Batch;
import com.tavant.spring.boot.DataProcessor.entities.College;
import com.tavant.spring.boot.DataProcessor.entities.Department;
import com.tavant.spring.boot.DataProcessor.entities.Student;
import com.tavant.spring.boot.DataProcessor.exceptions.ObjectsMalformedException;
import com.tavant.spring.boot.DataProcessor.exceptions.ResourcesNotFoundException;
import com.tavant.spring.boot.DataProcessor.repositories.CollegeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;

    public College createCollege(College college) throws ObjectsMalformedException {
        if (college == null || college.getName() == null || college.getAddress() == null) {
            throw new ObjectsMalformedException("College data is malformed");
        }

        // Save the college and its associated entities
        return collegeRepository.save(college);
    }
    
    public List<College> findAll() {
        return collegeRepository.findAll();
    }
    

    public College findCollegeByName(String name) throws ResourcesNotFoundException, ObjectsMalformedException {
        if (name == null || name.isEmpty()) {
            throw new ObjectsMalformedException("College name is null or empty. Cannot retrieve college.");
        }
        College college = collegeRepository.findByName(name);
        if (college == null) {
            throw new ResourcesNotFoundException("College not found with name: " + name);
        }
        return college;
    }

    @Transactional
    public int updateCollegeName(int id, String name) throws ResourcesNotFoundException, ObjectsMalformedException {
        if (name == null || name.isEmpty()) {
            throw new ObjectsMalformedException("College name is null or empty. Cannot update.");
        }
        Optional<College> college = collegeRepository.findById(id);
        if (!college.isPresent()) {
            throw new ResourcesNotFoundException("College not found with ID: " + id);
        }
        return collegeRepository.updateCollegeName(id, name);
    }

    @Transactional
    public int updateCollegeAddress(int id, String address) throws ResourcesNotFoundException, ObjectsMalformedException {
        if (address == null || address.isEmpty()) {
            throw new ObjectsMalformedException("College address is null or empty. Cannot update.");
        }
        Optional<College> college = collegeRepository.findById(id);
        if (!college.isPresent()) {
            throw new ResourcesNotFoundException("College not found with ID: " + id);
        }
        return collegeRepository.updateCollegeAddress(id, address);
    }

    public void deleteCollegeById(int id) throws ResourcesNotFoundException {
        Optional<College> college = collegeRepository.findById(id);
        if (!college.isPresent()) {
            throw new ResourcesNotFoundException("College not found with ID: " + id);
        }
        collegeRepository.deleteById(id);
    }

    public Optional<College> findCollegeById(int id) throws ResourcesNotFoundException {
        Optional<College> college = collegeRepository.findById(id);
        if (!college.isPresent()) {
            throw new ResourcesNotFoundException("College not found with ID: " + id);
        }
        return college;
    }
}
