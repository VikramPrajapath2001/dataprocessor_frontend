package com.tavant.spring.boot.DataProcessor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tavant.spring.boot.DataProcessor.entities.Student;
import com.tavant.spring.boot.DataProcessor.exceptions.ObjectsMalformedException;
import com.tavant.spring.boot.DataProcessor.exceptions.ResourcesNotFoundException;
import com.tavant.spring.boot.DataProcessor.services.StudentService;

import java.util.List;
import java.util.Map;

/**
 * REST controller for managing Student entities.
 */
@RestController
@RequestMapping("/api/students")
@CrossOrigin("http://localhost:4200")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * Retrieve a list of all students.
     * 
     * @return ResponseEntity with a list of Student objects and HTTP status.
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    /**
     * Retrieve a student by its ID.
     * 
     * @param id The ID of the student.
     * @return ResponseEntity with the Student object and HTTP status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        try {
            Student student = studentService.findStudentById(id);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (ResourcesNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update the name of a student with the given ID.
     * 
     * @param id The ID of the student.
     * @param name The new name of the student.
     * @return ResponseEntity with the number of updated rows and HTTP status.
     * @throws ObjectsMalformedException 
     */
    @PutMapping("/name/{id}")
    public ResponseEntity<Integer> updateStudentName(
            @PathVariable int id, 
            @RequestBody Map<String, String> body) throws ObjectsMalformedException {
        String name = body.get("name");
        if (name == null || name.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        try {
            int updatedCount = studentService.updateStudentName(id, name);
            return new ResponseEntity<>(updatedCount, HttpStatus.OK);
        } catch (ResourcesNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Delete a student by its ID.
     * 
     * @param id The ID of the student.
     * @return ResponseEntity with HTTP status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        try {
            studentService.deleteStudentById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourcesNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
