package com.tavant.spring.boot.DataProcessor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tavant.spring.boot.DataProcessor.entities.Batch;
import com.tavant.spring.boot.DataProcessor.entities.Student;
import com.tavant.spring.boot.DataProcessor.exceptions.ObjectsMalformedException;
import com.tavant.spring.boot.DataProcessor.exceptions.ResourcesNotFoundException;
import com.tavant.spring.boot.DataProcessor.repositories.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) throws ObjectsMalformedException {
        if (student == null || student.getName() == null || student.getBatch() == null) {
            throw new ObjectsMalformedException("Student object not created properly. Cannot save.");
        }
        return studentRepository.save(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    
    public Student findStudentById(int id) throws ResourcesNotFoundException {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new ResourcesNotFoundException("Student not found with ID: " + id);
        }
        return student;
    }


    @Transactional
    public int updateStudentName(int id, String name) throws ResourcesNotFoundException, ObjectsMalformedException {
        if (name == null || name.isEmpty()) {
            throw new ObjectsMalformedException("Student name is null or empty. Cannot update.");
        }
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new ResourcesNotFoundException("Student not found with ID: " + id);
        }
        return studentRepository.updateStudentName(id, name);
    }

    public void deleteStudentById(int id) throws ResourcesNotFoundException {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new ResourcesNotFoundException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }
}
